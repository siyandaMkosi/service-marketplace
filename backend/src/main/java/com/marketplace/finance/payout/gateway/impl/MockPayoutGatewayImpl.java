package com.marketplace.finance.payout.gateway.impl;

import com.marketplace.finance.payout.gateway.PayoutGateway;
import com.marketplace.finance.payout.gateway.dto.PayoutGatewayRequest;
import com.marketplace.finance.payout.gateway.dto.PayoutGatewayResponse;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class MockPayoutGatewayImpl implements PayoutGateway {

    @Override
    public PayoutGatewayResponse transfer(PayoutGatewayRequest request) {

        return PayoutGatewayResponse.builder()
            .successful(true)
            .gatewayReference(UUID.randomUUID().toString())
            .message("Payout processed successfully.")
            .build();

    }

}
