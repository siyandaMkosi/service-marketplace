package com.marketplace.service.entity;

import com.marketplace.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
    name = "service_categories",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uk_service_category_code",
            columnNames = "code"
        ),
        @UniqueConstraint(
            name = "uk_service_category_name",
            columnNames = "name"
        )
    }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceCategory extends BaseEntity {

    @Column(nullable = false, length = 50)
    private String code;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 500)
    private String description;

    @Builder.Default
    @Column(nullable = false)
    private boolean active = true;

}
