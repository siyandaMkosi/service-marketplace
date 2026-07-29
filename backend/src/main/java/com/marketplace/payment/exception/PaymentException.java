package com.marketplace.payment.exception;

import com.marketplace.common.exception.BusinessException;

public abstract class PaymentException extends BusinessException {

    protected PaymentException(String message) {
        super(message);
    }
}
