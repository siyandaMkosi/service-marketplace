package com.marketplace.marketplace.dto.response;

import com.marketplace.service.dto.response.ServiceCategoryResponse;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MarketplaceServiceResponse {

    private Long id;

    private String name;

    private String description;

    private BigDecimal basePrice;

    private Integer estimatedDurationMinutes;

    private ServiceCategoryResponse category;

    private Long providerId;

    private String businessName;

}
