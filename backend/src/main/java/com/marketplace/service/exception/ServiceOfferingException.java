package com.marketplace.service.exception;

import com.marketplace.common.exception.BusinessException;

public abstract class ServiceOfferingException extends BusinessException {
    protected ServiceOfferingException(String message) {
        super(message);
    }
}
