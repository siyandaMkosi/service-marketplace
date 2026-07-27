package com.marketplace.bookings.exception;

public class ProviderScheduleConflictException extends BookingException{
    public ProviderScheduleConflictException() {
        super("Scheduled date must match the customer's preferred date.");
    }

    public ProviderScheduleConflictException(String message) {
        super(message);
    }
}
