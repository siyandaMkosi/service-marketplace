package com.marketplace.service.entity;

import com.marketplace.common.entity.BaseEntity;
import com.marketplace.provider.entity.Provider;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(
    name = "service_offerings",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uk_provider_service_name",
            columnNames = {
                "provider_id",
                "name"
            }
        )
    }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceOffering extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
        name = "provider_id",
        nullable = false,
        foreignKey = @ForeignKey(name = "fk_service_provider")
    )
    private Provider provider;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
        name = "category_id",
        nullable = false,
        foreignKey = @ForeignKey(name = "fk_service_category")
    )
    private ServiceCategory category;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(length = 1000)
    private String description;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal basePrice;

    @Column(nullable = false)
    private Integer estimatedDurationMinutes;

    @Builder.Default
    @Column(nullable = false)
    private boolean active = true;
}
