package com.marketplace.auth.exception;

import org.springframework.http.HttpStatus;

public class CannotRevokeCurrentSessionException extends AuthenticationException {

    public CannotRevokeCurrentSessionException() {
        super("Use the logout endpoint to sign out of your current session.", HttpStatus.BAD_REQUEST);
    }
}
