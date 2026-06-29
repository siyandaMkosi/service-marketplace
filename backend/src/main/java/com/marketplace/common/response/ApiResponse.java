package com.marketplace.common.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ApiResponse<T> {

    private boolean success;

    private String message;

    private LocalDateTime timestamp;

    private String path;

    private T data;

}
