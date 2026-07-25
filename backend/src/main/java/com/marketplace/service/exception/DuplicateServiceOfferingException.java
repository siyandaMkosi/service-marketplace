package com.marketplace.service.exception;

public class DuplicateServiceOfferingException extends ServiceOfferingException {

    public DuplicateServiceOfferingException() {
        super("You already have this service offering under your profile.");
    }

}
