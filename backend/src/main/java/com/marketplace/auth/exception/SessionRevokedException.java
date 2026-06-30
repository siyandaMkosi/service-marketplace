package com.marketplace.auth.exception;

import org.springframework.http.HttpStatus;

public class SessionRevokedException extends AuthenticationException {

    public SessionRevokedException() {
        super("This session has been revoked.", HttpStatus.UNAUTHORIZED);
    }
}
