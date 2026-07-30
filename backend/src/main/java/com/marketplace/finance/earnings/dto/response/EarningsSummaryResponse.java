package com.marketplace.finance.earnings.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class EarningsSummaryResponse {

    private BigDecimal grossIncome;

    private BigDecimal totalCommission;

    private BigDecimal totalRefunds;

    private BigDecimal totalPayouts;

    private BigDecimal currentBalance;

    private long completedPayments;

}
