package com.marketplace.payment.dto.response;

import com.marketplace.payment.enums.PaymentGatewayType;
import com.marketplace.payment.enums.PaymentMethod;
import com.marketplace.payment.enums.PaymentStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class PaymentResponse {

    private Long id;

    private String transactionReference;

    private String gatewayReference;

    private String checkoutUrl;

    private PaymentStatus status;

    private BigDecimal amount;

    private String currency;

    private PaymentMethod paymentMethod;

    private PaymentGatewayType gateway;

    private LocalDateTime paidAt;

}
