package com.marketplace.common.response;

import jakarta.servlet.http.HttpServletRequest;

import java.time.LocalDateTime;

public class ApiResponseBuilder {

    private ApiResponseBuilder() {}

    public static <T> ApiResponse<T> success(
        String message,
        T data,
        HttpServletRequest request
    ) {

        return ApiResponse.<T>builder()
            .success(true)
            .message(message)
            .timestamp(LocalDateTime.now())
            .path(request.getRequestURI())
            .data(data)
            .build();
    }

}
