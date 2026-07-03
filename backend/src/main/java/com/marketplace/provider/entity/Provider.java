package com.marketplace.provider.entity;

import com.marketplace.common.entity.BaseEntity;
import com.marketplace.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(
    name = "providers",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uk_provider_user",
            columnNames = "user_id"
        )
    }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Provider extends BaseEntity {

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
        name = "user_id",
        nullable = false,
        unique = true
    )
    private User user;

    @Column(length = 150)
    private String businessName;

    @Column(nullable = false, length = 1000)
    private String description;

    @Column(nullable = false)
    private Integer yearsExperience;

    @Builder.Default
    @Column(nullable = false)
    private boolean verified = false;

    @Builder.Default
    @Column(nullable = false, precision = 3, scale = 2)
    private BigDecimal averageRating = BigDecimal.ZERO;

    @Builder.Default
    @Column(nullable = false)
    private int totalReviews = 0;

    public void verify() {
        this.verified = true;
    }

    public void unverify() {
        this.verified = false;
    }

    public void updateRating(BigDecimal averageRating, int totalReviews) {
        this.averageRating = averageRating;
        this.totalReviews = totalReviews;
    }

    public void assignUser(User user) {
        this.user = user;
    }

}
