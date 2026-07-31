package com.marketplace.finance.payout.exception;

import com.marketplace.common.exception.BusinessException;

public abstract class PayoutException extends BusinessException {
    protected PayoutException(String message) {
        super(message);
    }
}
