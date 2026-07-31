package com.marketplace.finance.payout.exception;

public class PayoutNotFoundException extends PayoutException{
    public PayoutNotFoundException() {
        super("Payout not found.");
    }

    public PayoutNotFoundException(Long payoutId) {
        super("Payout not found: " + payoutId +".");
    }
}
