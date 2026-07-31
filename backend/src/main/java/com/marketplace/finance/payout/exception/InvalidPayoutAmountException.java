package com.marketplace.finance.payout.exception;

public class InvalidPayoutAmountException extends PayoutException{
    public InvalidPayoutAmountException() {
        super("Invalid payout amount.");
    }
    public InvalidPayoutAmountException(String message) {
        super(message);
    }
}
