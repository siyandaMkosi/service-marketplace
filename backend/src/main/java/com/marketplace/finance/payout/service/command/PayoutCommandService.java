package com.marketplace.finance.payout.service.command;

import com.marketplace.common.reference.ReferenceGenerator;
import com.marketplace.finance.balance.service.BalanceService;
import com.marketplace.finance.ledger.facade.LedgerFacade;
import com.marketplace.finance.payout.dto.request.CreatePayoutRequest;
import com.marketplace.finance.payout.dto.response.PayoutResponse;
import com.marketplace.finance.payout.entity.Payout;
import com.marketplace.finance.payout.enums.PayoutStatus;
import com.marketplace.finance.payout.exception.InsufficientBalanceException;
import com.marketplace.finance.payout.exception.InvalidPayoutAmountException;
import com.marketplace.finance.payout.exception.PayoutNotFoundException;
import com.marketplace.finance.payout.exception.PendingPayoutAlreadyExistsException;
import com.marketplace.finance.payout.factory.PayoutFactory;
import com.marketplace.finance.payout.factory.PayoutGatewayRequestFactory;
import com.marketplace.finance.payout.gateway.PayoutGateway;
import com.marketplace.finance.payout.gateway.dto.PayoutGatewayRequest;
import com.marketplace.finance.payout.gateway.dto.PayoutGatewayResponse;
import com.marketplace.finance.payout.mapper.PayoutMapper;
import com.marketplace.finance.payout.repository.PayoutRepository;
import com.marketplace.provider.entity.Provider;
import com.marketplace.provider.facade.ProviderFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PayoutCommandService {

    private final PayoutRepository payoutRepository;

    private final ProviderFacade providerFacade;

    private final BalanceService balanceService;

    private final PayoutFactory payoutFactory;

    private final LedgerFacade ledgerFacade;

    private final PayoutMapper payoutMapper;

    private final PayoutGateway payoutGateway;

    private final PayoutGatewayRequestFactory payoutGatewayRequestFactory;

    public PayoutResponse requestPayout(CreatePayoutRequest request) {

        validatePayoutAmount(request.getAmount());

        Provider provider = providerFacade.findCurrentProvider();

        ensureProviderCanRequestPayout(provider,request.getAmount());

        Payout payout = payoutFactory.create(provider, request.getAmount());

        Payout saved = payoutRepository.save(payout);

        return payoutMapper.toResponse(saved);

    }

    public PayoutResponse approvePayout(Long payoutId) {

        Payout payout = getPayout(payoutId);
        payout.approve();
        payout.startProcessing();

        PayoutGatewayResponse gatewayResponse = payoutGateway.transfer(payoutGatewayRequestFactory.create(payout));
        boolean completedSuccessfully = gatewayResponse.isSuccessful();

        if (completedSuccessfully) {
            payout.complete(gatewayResponse.getGatewayReference());
        } else {
            payout.fail(gatewayResponse.getMessage());
        }

        Payout savedPayout = payoutRepository.save(payout);

        if (completedSuccessfully) {
            ledgerFacade.recordPayout(savedPayout);
        }

        return payoutMapper.toResponse(savedPayout);

    }

    public PayoutResponse rejectPayout(Long payoutId, String reason) {

        Payout payout = getPayout(payoutId);

        payout.reject(reason);

        Payout saved = payoutRepository.save(payout);

        return payoutMapper.toResponse(saved);

    }

    public PayoutResponse startProcessing(Long payoutId) {

        Payout payout = getPayout(payoutId);

        payout.startProcessing();

        Payout saved = payoutRepository.save(payout);

        return payoutMapper.toResponse(saved);

    }

    public PayoutResponse complete(Long payoutId, String gatewayReference) {

        Payout payout = getPayout(payoutId);

        payout.complete(gatewayReference);

        Payout saved = payoutRepository.save(payout);

        ledgerFacade.recordPayout(saved);

        return payoutMapper.toResponse(saved);

    }

    public PayoutResponse fail(Long payoutId, String reason) {

        Payout payout = getPayout(payoutId);

        payout.fail(reason);

        Payout saved = payoutRepository.save(payout);

        return payoutMapper.toResponse(saved);

    }

    private void validatePayoutAmount(BigDecimal amount) {

        if (amount == null) {
            throw new InvalidPayoutAmountException("Payout amount is required.");
        }

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidPayoutAmountException("Payout amount must be greater than zero.");
        }

    }

    private void ensureProviderCanRequestPayout(Provider provider, BigDecimal requestedAmount) {

        ensureProviderHasEnoughBalance(requestedAmount);

        ensureProviderHasNoActivePayout(provider);

    }

    private void ensureProviderHasEnoughBalance(BigDecimal requestedAmount) {

        BigDecimal currentBalance = balanceService.calculateCurrentProviderBalance();

        if (currentBalance.compareTo(requestedAmount) < 0) {
            throw new InsufficientBalanceException(currentBalance, requestedAmount);
        }

    }

    private void ensureProviderHasNoActivePayout(Provider provider) {

        boolean exists = payoutRepository.existsByProviderAndStatusIn(
                provider,
                List.of(
                    PayoutStatus.PENDING,
                    PayoutStatus.APPROVED,
                    PayoutStatus.PROCESSING
                )
            );

        if (exists) {
            throw new PendingPayoutAlreadyExistsException(provider.getId());
        }

    }

    private Payout getPayout(Long payoutId) {

        return payoutRepository.findById(payoutId)
            .orElseThrow(() -> new PayoutNotFoundException(payoutId));

    }

}
