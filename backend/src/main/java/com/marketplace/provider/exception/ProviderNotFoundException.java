package com.marketplace.provider.exception;

public class ProviderNotFoundException extends ProviderException {

    public ProviderNotFoundException() {
        super("Provider profile not found.");
    }

}
