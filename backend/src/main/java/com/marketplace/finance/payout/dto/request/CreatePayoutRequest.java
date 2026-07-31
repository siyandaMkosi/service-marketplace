package com.marketplace.finance.payout.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class CreatePayoutRequest {

    @NotNull(message = "Amount is required.")
    @DecimalMin(
        value = "0.01",
        inclusive = true,
        message = "Amount must be greater than zero."
    )
    @Digits(
        integer = 17,
        fraction = 2,
        message = "Invalid monetary amount."
    )
    private BigDecimal amount;

}
