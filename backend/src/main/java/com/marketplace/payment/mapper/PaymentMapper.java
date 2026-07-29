package com.marketplace.payment.mapper;

import com.marketplace.payment.dto.response.PaymentResponse;
import com.marketplace.payment.entity.Payment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    PaymentResponse toResponse(Payment payment);

}
