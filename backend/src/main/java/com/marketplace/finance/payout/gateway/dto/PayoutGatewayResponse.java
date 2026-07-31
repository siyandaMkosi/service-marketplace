package com.marketplace.finance.payout.gateway.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PayoutGatewayResponse {

    private boolean successful;

    private String gatewayReference;

    private String message;

}
