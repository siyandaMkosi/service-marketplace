package com.marketplace.finance.payout.gateway.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class PayoutGatewayRequest {

    private String payoutReference;

    private BigDecimal amount;

}
