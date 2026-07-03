package com.marketplace.provider.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProviderRegistrationRequest {

    @Size(max = 150)
    private String businessName;

    @NotBlank(message = "Description is required.")
    @Size(max = 1000)
    private String description;

    @Min(value = 0)
    @Max(value = 80)
    private Integer yearsExperience;

}
