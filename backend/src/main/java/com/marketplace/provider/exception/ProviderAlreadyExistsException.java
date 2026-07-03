package com.marketplace.provider.exception;

public class ProviderAlreadyExistsException extends ProviderException {

    public ProviderAlreadyExistsException() {
        super("You already have a provider profile.");
    }

}
