package com.marketplace.auth.entity;

import com.marketplace.common.entity.BaseEntity;
import com.marketplace.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(
    name = "user_sessions",
    indexes = {
        @Index(
            name = "idx_refresh_token",
            columnList = "refresh_token"
        )
    }
)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSession extends BaseEntity {


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "user_id",
        nullable = false
    )
    private User user;


    @Column(
        name = "refresh_token",
        nullable = false,
        unique = true,
        length = 512
    )
    private String refreshToken;


    @Column(
        nullable = false
    )
    private LocalDateTime expiresAt;


    @Column(
        nullable = false
    )
    private LocalDateTime lastUsedAt;


    @Column(
        nullable = false
    )
    private boolean revoked;


    @Column(
        length = 100
    )
    private String deviceName;


    @Column(
        length = 100
    )
    private String ipAddress;


    @Column(
        length = 500
    )
    private String userAgent;


    public boolean isExpired() {

        return expiresAt.isBefore(
            LocalDateTime.now()
        );
    }

}
