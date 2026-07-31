package com.marketplace.finance.payout.exception;

public class PendingPayoutAlreadyExistsException extends PayoutException{
    public PendingPayoutAlreadyExistsException() {
        super("Pending payout already exists.");
    }

    public PendingPayoutAlreadyExistsException(Long providerId) {
        super("Pending payout already exists for provider: " + providerId + ".");
    }
}
