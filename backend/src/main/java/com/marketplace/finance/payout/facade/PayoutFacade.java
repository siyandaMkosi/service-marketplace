package com.marketplace.finance.payout.facade;

import com.marketplace.finance.payout.dto.request.CreatePayoutRequest;
import com.marketplace.finance.payout.dto.response.PayoutResponse;
import com.marketplace.finance.payout.service.PayoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PayoutFacade {

    private final PayoutService payoutService;

    public PayoutResponse requestPayout(CreatePayoutRequest request) {
        return payoutService.requestPayout(request);
    }

    public PayoutResponse approvePayout(Long payoutId) {
        return payoutService.approvePayout(payoutId);
    }

    public PayoutResponse rejectPayout(Long payoutId, String reason) {
        return payoutService.rejectPayout(payoutId, reason);
    }

    public List<PayoutResponse> getCurrentProviderPayouts() {
        return payoutService.getCurrentProviderPayouts();
    }

    public PayoutResponse getPayout(Long payoutId) {
        return payoutService.getPayout(payoutId);
    }

}
