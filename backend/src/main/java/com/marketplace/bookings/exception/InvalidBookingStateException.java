package com.marketplace.bookings.exception;

public class InvalidBookingStateException extends BookingException{
    public InvalidBookingStateException() {
        super("Only pending bookings can be accepted.");
    }

    public InvalidBookingStateException(String message) {
        super(message);
    }
}
