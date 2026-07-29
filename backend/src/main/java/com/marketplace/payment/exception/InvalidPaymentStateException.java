package com.marketplace.payment.exception;

public class InvalidPaymentStateException extends PaymentException{

    public InvalidPaymentStateException() {
        super("Invalid payment state.");
    }

    public InvalidPaymentStateException(String message) {
        super(message);
    }
}
