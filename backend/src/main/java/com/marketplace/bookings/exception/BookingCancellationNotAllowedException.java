package com.marketplace.bookings.exception;

public class BookingCancellationNotAllowedException extends BookingException{

    public BookingCancellationNotAllowedException() {
        super("This booking can no longer be cancelled because payment has already been received.");
    }

    public BookingCancellationNotAllowedException(String message) {
        super(message);
    }
}
