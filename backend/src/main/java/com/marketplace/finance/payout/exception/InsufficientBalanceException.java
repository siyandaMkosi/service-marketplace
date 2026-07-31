package com.marketplace.finance.payout.exception;

import java.math.BigDecimal;

public class InsufficientBalanceException extends PayoutException{
    public InsufficientBalanceException() {
        super("Insufficient Balance for payout");
    }

    public InsufficientBalanceException(BigDecimal currentBalance, BigDecimal requestedAmount ) {
        super("Insufficient Balance for payout. Your current balance is: " + currentBalance + " and the amount requested is:  "+ requestedAmount+"." );
    }
}
