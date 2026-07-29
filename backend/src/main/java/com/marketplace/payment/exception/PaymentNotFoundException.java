package com.marketplace.payment.exception;

public class PaymentNotFoundException extends PaymentException{

    public PaymentNotFoundException() {
        super("Payment not found.");
    }

    public PaymentNotFoundException(String message) {
        super(message);
    }
}
