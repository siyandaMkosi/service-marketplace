package com.marketplace.payment.exception;

public class DuplicatePaymentException extends PaymentException{

    public DuplicatePaymentException() {
        super("Payment already exists.");
    }

    public DuplicatePaymentException(String message) {
        super(message);
    }
}
