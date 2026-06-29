package com.marketplace.auth.entity;

import com.marketplace.common.entity.BaseEntity;
import com.marketplace.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "refresh_tokens")
@Getter
@Setter
public class RefreshToken extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String token;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private User user;

    private LocalDateTime expiryDate;

    private boolean revoked;

    private boolean expired;
}
