package com.marketplace.service.dto.request;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceOfferingUpdateRequest {

    private Long categoryId;

    private String name;

    private String description;

    private BigDecimal basePrice;

    private Integer estimatedDurationMinutes;

    private Boolean active;

}
