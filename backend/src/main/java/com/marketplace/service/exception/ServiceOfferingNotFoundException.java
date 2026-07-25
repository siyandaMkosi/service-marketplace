package com.marketplace.service.exception;

public class ServiceOfferingNotFoundException extends ServiceOfferingException{
    public ServiceOfferingNotFoundException() {
        super("Service offering not found.");
    }
}
