package com.marketplace.payment.service.query;

import com.marketplace.payment.dto.response.PaymentResponse;
import com.marketplace.payment.entity.Payment;
import com.marketplace.payment.exception.PaymentNotFoundException;
import com.marketplace.payment.mapper.PaymentMapper;
import com.marketplace.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class  PaymentQueryService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    public PaymentResponse getByTransactionReference(String transactionReference) {

        Payment payment = paymentRepository.findByTransactionReference(transactionReference)
                .orElseThrow(
                    PaymentNotFoundException::new
                );

        return paymentMapper.toResponse(payment);

    }

}
