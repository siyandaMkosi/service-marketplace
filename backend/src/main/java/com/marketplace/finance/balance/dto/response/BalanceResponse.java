package com.marketplace.finance.balance.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class BalanceResponse {

    private BigDecimal currentBalance;

}
