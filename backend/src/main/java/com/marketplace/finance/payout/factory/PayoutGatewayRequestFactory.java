package com.marketplace.finance.payout.factory;

import com.marketplace.finance.payout.entity.Payout;
import com.marketplace.finance.payout.gateway.dto.PayoutGatewayRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PayoutGatewayRequestFactory {

    public PayoutGatewayRequest create(Payout payout) {

        return PayoutGatewayRequest.builder()
            .payoutReference(payout.getPayoutReference())
            .amount(payout.getAmount())
            .build();

    }

}
