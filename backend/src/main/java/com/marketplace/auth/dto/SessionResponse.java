package com.marketplace.auth.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SessionResponse {

    private Long id;

    private String deviceName;

    private String ipAddress;

    private String userAgent;

    private LocalDateTime lastUsedAt;

    private LocalDateTime expiresAt;

    private boolean current;
}
