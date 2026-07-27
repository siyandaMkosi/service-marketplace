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
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
@Getter
@Setter
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
}
