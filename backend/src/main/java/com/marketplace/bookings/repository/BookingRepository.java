package com.marketplace.bookings.repository;

import com.marketplace.bookings.entity.Booking;
import com.marketplace.bookings.enums.BookingStatus;
import com.marketplace.provider.entity.Provider;
import com.marketplace.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findAllByCustomerOrderByCreatedAtDesc(User customer);

    List<Booking> findAllByProviderOrderByCreatedAtDesc(Provider provider);

    boolean existsByProviderAndStatusAndScheduledStartLessThanAndScheduledEndGreaterThan(Provider provider, BookingStatus status, LocalDateTime scheduledEnd, LocalDateTime scheduledStart);

    List<Booking> findAllByProviderAndStatusOrderByCreatedAtAsc(Provider provider, BookingStatus status);
}
