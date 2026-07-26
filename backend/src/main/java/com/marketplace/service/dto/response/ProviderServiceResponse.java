package com.marketplace.service.dto.response;

import java.math.BigDecimal;

public class ProviderServiceResponse {

    private Long id;

    private String name;

    private String description;

    private BigDecimal basePrice;

    private Integer estimatedDurationMinutes;

    private ServiceCategoryResponse category;

    private boolean active;

}
