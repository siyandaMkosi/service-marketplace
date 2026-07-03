package com.marketplace.user.exception;

import com.marketplace.common.exception.BusinessException;

public abstract class UserException extends BusinessException {

    protected UserException(String message) {
        super(message);
    }

}
