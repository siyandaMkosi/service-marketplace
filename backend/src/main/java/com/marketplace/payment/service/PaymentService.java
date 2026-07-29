package com.marketplace.payment.service;

import com.marketplace.payment.dto.request.PaymentRequest;
import com.marketplace.payment.dto.response.PaymentResponse;
import com.marketplace.payment.service.command.PaymentCommandService;
import com.marketplace.payment.service.query.PaymentQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class  PaymentService {

    private final PaymentCommandService commandService;

    private final PaymentQueryService queryService;

    public PaymentResponse createPayment(Long bookingId, PaymentRequest request) {

        return commandService.createPayment(bookingId, request);

    }

    public PaymentResponse verifyPayment(String transactionReference) {

        return commandService.verifyPayment(transactionReference);

    }

    public PaymentResponse getPayment(
        String transactionReference
    ) {

        return queryService.getByTransactionReference(
            transactionReference
        );

    }

}
