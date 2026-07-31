package com.marketplace.finance.payout.exception;

import com.marketplace.finance.payout.enums.PayoutStatus;

public class InvalidPayoutStateException extends PayoutException{
    public InvalidPayoutStateException() {
        super("Invalid payout state.");
    }

    public InvalidPayoutStateException(String payoutReference, PayoutStatus status, PayoutStatus expectedStatus){
        super("Invalid payout state. Status cannot be " + status + ". Expected state is: " + expectedStatus + " for payout : " + payoutReference );
    }
}
