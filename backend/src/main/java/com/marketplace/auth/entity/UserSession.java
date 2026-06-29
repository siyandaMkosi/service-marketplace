package com.marketplace.auth.entity;

import com.marketplace.common.entity.BaseEntity;
import com.marketplace.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_sessions")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSession extends BaseEntity {

    @Column(nullable = false, unique = true, length = 512)
    private String refreshToken;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private LocalDateTime expiresAt;

    @Column(nullable = false)
    private LocalDateTime lastUsedAt;

    @Column(nullable = false)
    private boolean revoked;

    @Column(nullable = false)
    private String deviceName;

    @Column(nullable = false)
    private String ipAddress;

    @Column(nullable = false)
    private String userAgent;
}
