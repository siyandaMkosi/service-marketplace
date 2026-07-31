package com.marketplace.finance.payout.service;

import com.marketplace.finance.payout.dto.request.CreatePayoutRequest;
import com.marketplace.finance.payout.dto.response.PayoutResponse;
import com.marketplace.finance.payout.service.command.PayoutCommandService;
import com.marketplace.finance.payout.service.query.PayoutQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PayoutService {

    private final PayoutCommandService payoutCommandService;
    private final PayoutQueryService payoutQueryService;

    public PayoutResponse requestPayout(CreatePayoutRequest request) {
        return payoutCommandService.requestPayout(request);
    }

    public PayoutResponse approvePayout(Long payoutId) {
        return payoutCommandService.approvePayout(payoutId);
    }

    public PayoutResponse rejectPayout(Long payoutId, String reason) {
        return payoutCommandService.rejectPayout(payoutId, reason);
    }

    @Transactional(readOnly = true)
    public List<PayoutResponse> getCurrentProviderPayouts() {
        return payoutQueryService.getCurrentProviderPayouts();
    }

    @Transactional(readOnly = true)
    public PayoutResponse getPayout(
        Long payoutId
    ) {
        return payoutQueryService.getPayout(
            payoutId
        );
    }

}
