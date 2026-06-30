package com.marketplace.auth.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public abstract class AuthenticationException extends RuntimeException {

    private final HttpStatus status;

    protected AuthenticationException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

}
