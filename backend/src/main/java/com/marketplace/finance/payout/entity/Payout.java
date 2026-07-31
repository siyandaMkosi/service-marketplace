package com.marketplace.finance.payout.entity;

import com.marketplace.finance.payout.enums.PayoutStatus;
import com.marketplace.finance.payout.exception.InvalidPayoutStateException;
import com.marketplace.provider.entity.Provider;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "payouts")
public class Payout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, updatable = false)
    private String payoutReference;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "provider_id")
    private Provider provider;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PayoutStatus status;

    @Column(nullable = false, updatable = false)
    private LocalDateTime requestedAt;

    private LocalDateTime approvedAt;

    private LocalDateTime processingAt;

    private LocalDateTime completedAt;

    private LocalDateTime rejectedAt;

    private LocalDateTime failedAt;

    private String gatewayReference;

    private String rejectionReason;

    private String failureReason;

    public void approve() {

        validateStatus(PayoutStatus.PENDING);

        this.status = PayoutStatus.APPROVED;
        this.approvedAt = LocalDateTime.now();

    }

    public void reject(String reason) {

        validateStatus(PayoutStatus.PENDING);

        this.status = PayoutStatus.REJECTED;
        this.rejectionReason = reason;
        this.rejectedAt = LocalDateTime.now();

    }

    public void startProcessing() {

        validateStatus(PayoutStatus.APPROVED);

        this.status = PayoutStatus.PROCESSING;
        this.processingAt = LocalDateTime.now();

    }

    public void complete(String gatewayReference) {

        validateStatus(PayoutStatus.PROCESSING);

        this.status = PayoutStatus.COMPLETED;
        this.gatewayReference = gatewayReference;
        this.completedAt = LocalDateTime.now();

    }

    public void fail(String reason) {

        validateStatus(PayoutStatus.PROCESSING);

        this.status = PayoutStatus.FAILED;
        this.failureReason = reason;
        this.failedAt = LocalDateTime.now();

    }

    private void validateStatus(PayoutStatus expectedStatus) {

        if (status != expectedStatus) {

            throw new InvalidPayoutStateException(
                payoutReference,
                status,
                expectedStatus
            );

        }

    }

}
