package com.marketplace.provider.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProviderUpdateRequest {

    @Size(max = 150)
    private String businessName;

    @Size(max = 1000)
    private String description;

    @Min(0)
    @Max(80)
    private Integer yearsExperience;

}
