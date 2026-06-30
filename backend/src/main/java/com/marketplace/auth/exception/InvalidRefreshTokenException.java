package com.marketplace.auth.exception;

import org.springframework.http.HttpStatus;

public class InvalidRefreshTokenException extends AuthenticationException {

    public InvalidRefreshTokenException() {
        super("Invalid refresh token.", HttpStatus.UNAUTHORIZED);
    }
}
