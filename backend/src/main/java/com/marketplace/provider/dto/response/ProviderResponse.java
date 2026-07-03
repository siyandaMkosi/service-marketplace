package com.marketplace.provider.dto.response;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProviderResponse {

    private Long id;

    private String businessName;

    private String description;

    private Integer yearsExperience;

    private boolean verified;

    private BigDecimal averageRating;

    private int totalReviews;

}
