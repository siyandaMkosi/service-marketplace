package com.marketplace.bookings.exception;

public class InvalidBookingScheduleException extends BookingException{
    public InvalidBookingScheduleException() {
        super("Scheduled date must match the customer's preferred date.");
    }

    public InvalidBookingScheduleException(String message) {
        super(message);
    }
}
