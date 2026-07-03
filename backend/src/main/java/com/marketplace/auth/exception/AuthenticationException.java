package com.marketplace.auth.exception;

import com.marketplace.common.exception.BusinessException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public abstract class AuthenticationException extends BusinessException {

    private final HttpStatus status;

    protected AuthenticationException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

}
