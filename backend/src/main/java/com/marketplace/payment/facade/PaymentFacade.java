package com.marketplace.payment.facade;

import com.marketplace.payment.dto.request.PaymentRequest;
import com.marketplace.payment.dto.response.PaymentResponse;
import com.marketplace.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentFacade {

    private final PaymentService paymentService;

    public PaymentResponse createPayment(Long bookingId, PaymentRequest request) {

        return paymentService.createPayment(bookingId, request);

    }

    public PaymentResponse verifyPayment(String transactionReference) {

        return paymentService.verifyPayment(transactionReference);

    }

    public PaymentResponse getPayment(String transactionReference) {

        return paymentService.getPayment(transactionReference);

    }

}
