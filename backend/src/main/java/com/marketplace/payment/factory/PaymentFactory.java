package com.marketplace.payment.factory;

import com.marketplace.bookings.entity.Booking;
import com.marketplace.payment.entity.Payment;
import com.marketplace.payment.enums.PaymentGatewayType;
import com.marketplace.payment.enums.PaymentMethod;
import com.marketplace.payment.enums.PaymentStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Currency;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class  PaymentFactory {

    public Payment create(Booking booking, PaymentMethod paymentMethod) {

        return Payment.builder()
            .booking(booking)
            .customer(booking.getCustomer())
            .provider(booking.getProvider())
            .amount(booking.getAgreedPrice())
            .currency(Currency.getInstance("ZAR").getCurrencyCode())
            .status(PaymentStatus.PENDING)
            .paymentMethod(paymentMethod)
            .gateway(PaymentGatewayType.MOCK)
            .transactionReference(generateTransactionReference())
            .build();

    }

    private String generateTransactionReference() {

        return "PAY-" +
            UUID.randomUUID()
                .toString()
                .replace("-", "")
                .substring(0, 16)
                .toUpperCase();

    }

}
