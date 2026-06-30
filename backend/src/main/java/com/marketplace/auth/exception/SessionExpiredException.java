package com.marketplace.auth.exception;

import org.springframework.http.HttpStatus;

public class SessionExpiredException extends AuthenticationException {

    public SessionExpiredException() {
        super("The session has expired.", HttpStatus.UNAUTHORIZED);
    }
}
