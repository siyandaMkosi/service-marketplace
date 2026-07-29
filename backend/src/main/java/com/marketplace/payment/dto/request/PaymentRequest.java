package com.marketplace.payment.dto.request;

import com.marketplace.payment.enums.PaymentMethod;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRequest {

    @NotNull
    private PaymentMethod paymentMethod;

}
