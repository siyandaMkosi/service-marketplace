package com.marketplace.finance.payout.service.query;

import com.marketplace.finance.payout.dto.response.PayoutResponse;
import com.marketplace.finance.payout.entity.Payout;
import com.marketplace.finance.payout.exception.PayoutNotFoundException;
import com.marketplace.finance.payout.mapper.PayoutMapper;
import com.marketplace.finance.payout.repository.PayoutRepository;
import com.marketplace.provider.entity.Provider;
import com.marketplace.provider.facade.ProviderFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PayoutQueryService {

    private final PayoutRepository payoutRepository;
    private final ProviderFacade providerFacade;
    private final PayoutMapper payoutMapper;

    public List<PayoutResponse> getCurrentProviderPayouts() {

        Provider provider = providerFacade.findCurrentProvider();

        return payoutRepository
            .findAllByProviderOrderByRequestedAtDesc(provider)
            .stream()
            .map(payoutMapper::toResponse)
            .toList();

    }

    public PayoutResponse getPayout(Long payoutId) {

        Payout payout = payoutRepository.findById(payoutId)
            .orElseThrow(() ->
                new PayoutNotFoundException(payoutId));

        return payoutMapper.toResponse(payout);

    }

}
