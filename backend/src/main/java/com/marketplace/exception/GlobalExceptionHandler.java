package com.marketplace.exception;

import com.marketplace.common.response.ApiErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ApiErrorResponse> handleDuplicateResourceException(
        DuplicateResourceException ex,
        HttpServletRequest request
    ) {
        ApiErrorResponse response = ApiErrorResponse.builder()
            .timestamp(LocalDateTime.now())
            .success(false)
            .message(ex.getMessage())
            .path(request.getRequestURI())
            .build();

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleValidationException(
        MethodArgumentNotValidException ex,
        HttpServletRequest request
    ) {
        Map<String, String> validationErrors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
            validationErrors.put(error.getField(), error.getDefaultMessage())
        );

        ApiErrorResponse response = ApiErrorResponse.builder()
            .timestamp(LocalDateTime.now())
            .success(false)
            .message("Validation failed")
            .path(request.getRequestURI())
            .errors(validationErrors)
            .build();

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleGenericException(
        Exception ex,
        HttpServletRequest request
    ) {
        ApiErrorResponse response = ApiErrorResponse.builder()
            .timestamp(LocalDateTime.now())
            .success(false)
            .message("An unexpected error occurred")
            .path(request.getRequestURI())
            .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
