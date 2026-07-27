package com.marketplace.bookings.exception;

import com.marketplace.common.exception.BusinessException;

public abstract class BookingException extends BusinessException {
    protected BookingException(String message) {
        super(message);
    }
}
