package com.marketplace.payment.gateway.impl;

import com.marketplace.payment.entity.Payment;
import com.marketplace.payment.enums.PaymentStatus;
import com.marketplace.payment.gateway.PaymentGateway;
import com.marketplace.payment.gateway.dto.PaymentGatewayResponse;
import com.marketplace.payment.gateway.dto.PaymentGatewayVerificationResponse;
import org.springframework.stereotype.Component;

@Component
public class MockPaymentGateway
    implements PaymentGateway {

    @Override
    public PaymentGatewayResponse initiatePayment(Payment payment) {

        return PaymentGatewayResponse.builder()
            .successful(true)
            .transactionReference(payment.getTransactionReference())
            .gatewayReference("MOCK-" + payment.getTransactionReference())
            .checkoutUrl("https://mock.marketplace/pay/" + payment.getTransactionReference())
            .message("Mock payment initiated successfully.")
            .build();

    }

    @Override
    public PaymentGatewayVerificationResponse verifyPayment(String transactionReference) {

        return PaymentGatewayVerificationResponse.builder()
            .paymentStatus(PaymentStatus.PAID)
            .gatewayReference("MOCK-" + transactionReference)
            .message("Mock payment verified.")
            .build();

    }

    @Override
    public void refund(Payment payment) {

        // No-op for mock implementation

    }

}
