package com.marketplace.auth.exception;

import org.springframework.http.HttpStatus;

public class SessionNotFoundException extends AuthenticationException {

    public SessionNotFoundException() {
        super("Session not found.", HttpStatus.NOT_FOUND);
    }

}
