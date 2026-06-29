package com.marketplace.common.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Builder
public class ApiErrorResponse {

    private boolean success;

    private String message;

    private LocalDateTime timestamp;

    private String path;

    private Map<String, String> errors;

}
