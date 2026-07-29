package com.marketplace.payment.entity;

import com.marketplace.bookings.entity.Booking;
import com.marketplace.common.entity.BaseEntity;
import com.marketplace.payment.enums.PaymentGatewayType;
import com.marketplace.payment.enums.PaymentMethod;
import com.marketplace.payment.enums.PaymentStatus;
import com.marketplace.provider.entity.Provider;
import com.marketplace.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(
    name = "payments",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uk_payment_booking",
            columnNames = "booking_id"
        ),
        @UniqueConstraint(
            name = "uk_payment_transaction_reference",
            columnNames = "transaction_reference"
        )
    }
)
public class Payment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    // One booking can only ever have one payment.

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
        name = "booking_id",
        nullable = false
    )
    private Booking booking;


    // Customer that made the payment.

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
        name = "customer_id",
        nullable = false
    )
    private User customer;


    // Provider receiving the payment.

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
        name = "provider_id",
        nullable = false
    )
    private Provider provider;


    // Financial snapshot.

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal amount;

    @Column(nullable = false, length = 3)
    private String currency;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private PaymentStatus status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private PaymentGatewayType gateway;


     // Our own transaction reference.

    @Column(
        name = "transaction_reference",
        nullable = false,
        unique = true,
        length = 100
    )
    private String transactionReference;


    // Reference returned by the payment gateway.

    @Column(length = 100)
    private String gatewayReference;

    private LocalDateTime paidAt;

    private LocalDateTime refundedAt;

    @Column(length = 1000)
    private String failureReason;


    // Domain Behaviour
    public boolean isPending() {
        return status == PaymentStatus.PENDING;
    }

    public boolean isProcessing() {
        return status == PaymentStatus.PROCESSING;
    }

    public boolean isPaid() {
        return status == PaymentStatus.PAID;
    }

    public boolean isFailed() {
        return status == PaymentStatus.FAILED;
    }

    public boolean isRefunded() {
        return status == PaymentStatus.REFUNDED;
    }

    public void markProcessing() {
        this.status = PaymentStatus.PROCESSING;
    }

    public void markFailed(String reason) {
        this.status = PaymentStatus.FAILED;
        this.failureReason = reason;
    }

    public void refund() {

        if (!isPaid()) {
            throw new IllegalStateException(
                "Only paid payments can be refunded."
            );
        }

        this.status = PaymentStatus.REFUNDED;
        this.refundedAt = LocalDateTime.now();

    }

    public void initiated(String gatewayReference) {

        this.status = PaymentStatus.PROCESSING;
        this.gatewayReference = gatewayReference;

    }

    public void markPaid(String gatewayReference) {
        if (isPaid()) {
            throw new IllegalStateException(
                "Payment has already been marked as paid."
            );
        }
        this.status = PaymentStatus.PAID;
        this.gatewayReference = gatewayReference;
        this.paidAt = LocalDateTime.now();
    }

}
