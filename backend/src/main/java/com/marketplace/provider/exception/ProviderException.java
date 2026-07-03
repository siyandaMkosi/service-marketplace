package com.marketplace.provider.exception;

import com.marketplace.common.exception.BusinessException;

public abstract class ProviderException extends BusinessException {

    protected ProviderException(String message) {
        super(message);
    }

}
