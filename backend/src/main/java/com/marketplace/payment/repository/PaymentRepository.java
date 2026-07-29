package com.marketplace.payment.repository;

import com.marketplace.bookings.entity.Booking;
import com.marketplace.payment.entity.Payment;
import com.marketplace.payment.enums.PaymentStatus;
import com.marketplace.provider.entity.Provider;
import com.marketplace.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Optional<Payment> findByBooking(Booking booking);

    Optional<Payment> findByTransactionReference(String transactionReference);

    List<Payment> findAllByCustomerOrderByCreatedAtDesc(User customer);

    List<Payment> findAllByProviderOrderByCreatedAtDesc(Provider provider);

    List<Payment> findAllByStatus(PaymentStatus status);

    boolean existsByBooking(Booking booking);

    List<Payment> findAllByProviderAndStatusOrderByPaidAtDesc(Provider provider, PaymentStatus status);

}
