package com.marketplace.payment.gateway.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PaymentGatewayResponse {

    private boolean successful;

    private String transactionReference;

    private String gatewayReference;

    private String checkoutUrl;

    private String message;

}
