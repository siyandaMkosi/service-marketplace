package com.marketplace.finance.payout.dto.response;

import com.marketplace.finance.payout.enums.PayoutStatus;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
public class PayoutResponse {

    private Long id;

    private String payoutReference;

    private BigDecimal amount;

    private PayoutStatus status;

    private String gatewayReference;

    private String rejectionReason;

    private String failureReason;

    private LocalDateTime requestedAt;

    private LocalDateTime approvedAt;

    private LocalDateTime processingAt;

    private LocalDateTime completedAt;

    private LocalDateTime rejectedAt;

    private LocalDateTime failedAt;

    private String currency;

}
