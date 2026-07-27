package com.marketplace.bookings.exception;

public class BookingNotFoundException extends BookingException{
    public BookingNotFoundException() {
        super("Booking not found.");
    }
}
