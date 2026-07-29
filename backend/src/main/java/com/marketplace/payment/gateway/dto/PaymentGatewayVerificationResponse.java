package com.marketplace.payment.gateway.dto;

import com.marketplace.payment.enums.PaymentStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PaymentGatewayVerificationResponse {

    private PaymentStatus paymentStatus;

    private String gatewayReference;

    private String message;

}
