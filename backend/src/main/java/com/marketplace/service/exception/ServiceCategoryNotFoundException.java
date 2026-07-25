package com.marketplace.service.exception;

public class ServiceCategoryNotFoundException extends ServiceOfferingException{
    public ServiceCategoryNotFoundException() {
        super("Service category not found.");
    }
}
