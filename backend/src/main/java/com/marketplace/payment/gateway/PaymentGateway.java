package com.marketplace.payment.gateway;

import com.marketplace.payment.entity.Payment;
import com.marketplace.payment.gateway.dto.PaymentGatewayResponse;
import com.marketplace.payment.gateway.dto.PaymentGatewayVerificationResponse;

public interface PaymentGateway {

    PaymentGatewayResponse initiatePayment(Payment payment);

    PaymentGatewayVerificationResponse verifyPayment(String transactionReference);

    void refund(Payment payment);

}
