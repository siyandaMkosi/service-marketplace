package com.marketplace.payment.service.command;

import com.marketplace.bookings.entity.Booking;
import com.marketplace.bookings.repository.BookingRepository;
import com.marketplace.bookings.exception.BookingNotFoundException;
import com.marketplace.payment.dto.request.PaymentRequest;
import com.marketplace.payment.dto.response.PaymentResponse;
import com.marketplace.payment.entity.Payment;
import com.marketplace.payment.enums.PaymentStatus;
import com.marketplace.payment.exception.DuplicatePaymentException;
import com.marketplace.payment.exception.InvalidPaymentStateException;
import com.marketplace.payment.exception.PaymentNotFoundException;
import com.marketplace.payment.factory.PaymentFactory;
import com.marketplace.payment.gateway.PaymentGateway;
import com.marketplace.payment.gateway.dto.PaymentGatewayResponse;
import com.marketplace.payment.gateway.dto.PaymentGatewayVerificationResponse;
import com.marketplace.payment.mapper.PaymentMapper;
import com.marketplace.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class  PaymentCommandService {

    private final BookingRepository bookingRepository;
    private final PaymentRepository paymentRepository;
    private final PaymentFactory paymentFactory;
    private final PaymentMapper paymentMapper;
    private final PaymentGateway paymentGateway;
    public PaymentResponse createPayment(Long bookingId, PaymentRequest request) {

        Booking booking = getBooking(bookingId);

        validateBookingCanBePaid(booking);

        validatePaymentDoesNotExist(booking);

        Payment payment = paymentFactory.create(booking, request.getPaymentMethod());

        Payment savedPayment = paymentRepository.save(payment);

        PaymentGatewayResponse gatewayResponse = paymentGateway.initiatePayment(savedPayment);

        savedPayment.initiated(gatewayResponse.getGatewayReference());

        paymentRepository.save(savedPayment);

        PaymentResponse response = paymentMapper.toResponse(savedPayment);

        response.setCheckoutUrl(gatewayResponse.getCheckoutUrl());

        return response;

    }

    public PaymentResponse verifyPayment(String transactionReference) {

        Payment payment = getPayment(transactionReference);

        validatePaymentCanBeVerified(payment);

        PaymentGatewayVerificationResponse verification = paymentGateway.verifyPayment(transactionReference);

        if (payment.isPaid()) {
            payment.markPaid(verification.getGatewayReference());

        } else {

            payment.markFailed(verification.getMessage());

        }

        Payment savedPayment = paymentRepository.save(payment);

        return paymentMapper.toResponse(savedPayment);

    }

    private void validatePaymentCanBeVerified(Payment payment) {

        if (payment.isPaid()) {

            throw new InvalidPaymentStateException(
                "Payment has already been verified."
            );

        }

        if (payment.isRefunded()) {

            throw new InvalidPaymentStateException(
                "Refunded payments cannot be verified."
            );

        }

    }

    private Booking getBooking(Long bookingId) {

        return bookingRepository.findById(bookingId)
            .orElseThrow(BookingNotFoundException::new);

    }

    private void validatePaymentDoesNotExist(Booking booking) {

        if (paymentRepository.existsByBooking(booking)) {

            throw new DuplicatePaymentException(
                "A payment already exists for this booking."
            );

        }

    }

    private void validateBookingCanBePaid(Booking booking) {

        if (!booking.isAccepted()) {

            throw new InvalidPaymentStateException(
                "Only accepted bookings can be paid for."
            );

        }

    }

    private Payment getPayment(String transactionReference) {

        return paymentRepository.findByTransactionReference(transactionReference)
            .orElseThrow(
                PaymentNotFoundException::new
            );

    }
}
