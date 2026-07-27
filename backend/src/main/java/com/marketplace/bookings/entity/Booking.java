package com.marketplace.bookings.entity;

import com.marketplace.bookings.enums.BookingStatus;
import com.marketplace.bookings.enums.BookingTimeWindow;
import com.marketplace.bookings.enums.PaymentStatus;
import com.marketplace.common.entity.BaseEntity;
import com.marketplace.provider.entity.Provider;
import com.marketplace.service.entity.ServiceOffering;
import com.marketplace.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private User customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "provider_id", nullable = false)
    private Provider provider;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_offering_id", nullable = false)
    private ServiceOffering serviceOffering;

    @Column(nullable = false)
    private String serviceName;

    @Column(length = 2000)
    private String serviceDescription;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal agreedPrice;

    @Column(nullable = false)
    private Integer estimatedDurationMinutes;

    @Column(nullable = false)
    private LocalDate preferredDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BookingTimeWindow preferredTimeWindow;

    private LocalDateTime scheduledStart;

    private LocalDateTime scheduledEnd;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BookingStatus status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus paymentStatus;

    @Column(length = 3000)
    private String customerNotes;

    @Column(length = 3000)
    private String providerNotes;

    @Column(nullable = false)
    private Boolean providerConfirmedTime = false;

    @Column(length = 1000)
    private String rejectionReason;

    @Column(length = 1000)
    private String cancellationReason;

    private LocalDateTime cancelledAt;

    private LocalDateTime completedAt;

    public void accept(LocalDateTime scheduledStart, LocalDateTime scheduledEnd, String providerNotes) {

        this.scheduledStart = scheduledStart;
        this.scheduledEnd = scheduledEnd;
        this.providerNotes = providerNotes;
        this.providerConfirmedTime = true;
        this.status = BookingStatus.ACCEPTED;

    }

    public void reject(String rejectionReason) {

        this.status = BookingStatus.REJECTED;
        this.rejectionReason = rejectionReason;

    }

    public void complete() {

        this.status = BookingStatus.COMPLETED;
        this.completedAt = LocalDateTime.now();

    }

    public void cancel(String cancellationReason) {

        this.status = BookingStatus.CANCELLED;
        this.cancellationReason = cancellationReason;
        this.cancelledAt = LocalDateTime.now();

    }

    public boolean isPending() {
        return status == BookingStatus.PENDING;
    }

    public boolean isAccepted() {
        return status == BookingStatus.ACCEPTED;
    }

    public boolean isRejected() {
        return status == BookingStatus.REJECTED;
    }

    public boolean isCancelled() {
        return status == BookingStatus.CANCELLED;
    }

    public boolean isCompleted() {
        return status == BookingStatus.COMPLETED;
    }

    public boolean isOwnedBy(Provider provider) {

        return this.provider.getId().equals(provider.getId());

    }

    public boolean isOwnedBy(User customer) {

        return this.customer.getId().equals(customer.getId());

    }

    public boolean hasPayment() {

        return paymentStatus == PaymentStatus.PAID;

    }

    public boolean hasProviderConfirmedTime() {

        return providerConfirmedTime;

    }

    public Duration getScheduledDuration() {

        if (scheduledStart == null || scheduledEnd == null) {
            return Duration.ZERO;
        }

        return Duration.between(
            scheduledStart,
            scheduledEnd
        );

    }
}
