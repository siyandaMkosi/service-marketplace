package com.marketplace.finance.payout.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RejectPayoutRequest {

    @NotBlank(message = "Rejection reason is required.")
    private String reason;

}
