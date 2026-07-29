package com.marketplace.bookings.service.command;


import com.marketplace.bookings.dto.request.BookingAcceptanceRequest;
import com.marketplace.bookings.dto.request.BookingCancellationRequest;
import com.marketplace.bookings.dto.request.BookingCreateRequest;
import com.marketplace.bookings.dto.request.BookingRejectionRequest;
import com.marketplace.bookings.dto.response.BookingResponse;
import com.marketplace.bookings.entity.Booking;
import com.marketplace.bookings.enums.BookingStatus;
import com.marketplace.bookings.enums.PaymentStatus;
import com.marketplace.bookings.exception.*;
import com.marketplace.bookings.factory.BookingFactory;
import com.marketplace.bookings.mapper.BookingMapper;
import com.marketplace.bookings.repository.BookingRepository;
import com.marketplace.provider.entity.Provider;
import com.marketplace.provider.service.ProviderService;
import com.marketplace.security.SecurityUtils;
import com.marketplace.service.entity.ServiceOffering;
import com.marketplace.service.exception.ServiceOfferingNotFoundException;
import com.marketplace.service.repository.ServiceOfferingRepository;
import com.marketplace.user.entity.User;
import com.marketplace.user.exception.UserNotFoundException;
import com.marketplace.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
@RequiredArgsConstructor
@Transactional
public class  BookingCommandService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final ServiceOfferingRepository serviceOfferingRepository;
    private final BookingFactory bookingFactory;
    private final BookingMapper bookingMapper;
    private final ProviderService providerService;


    public BookingResponse requestBooking(BookingCreateRequest request) {

        User customer = getAuthenticatedCustomer();

        ServiceOffering serviceOffering = getActiveServiceOffering(request.getServiceOfferingId());

        Booking booking = bookingFactory.createPendingBooking(customer, serviceOffering, request);

        Booking savedBooking = bookingRepository.save(booking);

        return bookingMapper.toResponse(savedBooking);

    }

    public BookingResponse acceptBooking(Long bookingId, BookingAcceptanceRequest request) {

        Booking booking = getPendingBookingForCurrentProvider(bookingId);
        validateScheduledDate(booking, request);
        validateScheduledTimes(request);
        validateTimeWindow(booking, request);
        validateNoOverlappingBookings(booking, request);
        booking.accept(request.getScheduledStart(), request.getScheduledEnd(), request.getProviderNotes());

        Booking savedBooking = bookingRepository.save(booking);

        return bookingMapper.toResponse(savedBooking);

    }


    public BookingResponse rejectBooking(Long bookingId, BookingRejectionRequest request) {
        Booking booking = getPendingBookingForCurrentProvider(bookingId);
        booking.reject(request.getRejectionReason());
        Booking savedBooking = bookingRepository.save(booking);
        return bookingMapper.toResponse(savedBooking);

    }

    public BookingResponse cancelBooking(Long bookingId, BookingCancellationRequest request) {

        Booking booking = getPendingBookingForCurrentCustomer(bookingId);

        booking.cancel(request.getCancellationReason());

        Booking savedBooking = bookingRepository.save(booking);

        return bookingMapper.toResponse(savedBooking);

    }

    public BookingResponse completeBooking(Long bookingId) {

        Booking booking = getAcceptedBookingForCurrentProvider(bookingId);

        validateCompletionTime(booking);

        booking.complete();

        Booking savedBooking = bookingRepository.save(booking);

        return bookingMapper.toResponse(savedBooking);

    }

    public BookingResponse cancelAcceptedBooking(Long bookingId, BookingCancellationRequest request) {

        Booking booking = getAcceptedBookingForCurrentProvider(bookingId);

        validatePaymentNotReceived(booking);

        booking.cancel(request.getCancellationReason());

        Booking savedBooking = bookingRepository.save(booking);

        return bookingMapper.toResponse(savedBooking);

    }

    private void validatePaymentNotReceived(Booking booking) {

        if (booking.hasPayment() && booking.hasPaid()) {
            throw new BookingCancellationNotAllowedException(
                "Bookings with completed payment require administrator intervention."
            );
        }

    }

    private Booking getAcceptedBookingForCurrentProvider(Long bookingId) {

        Provider provider = providerService.getCurrentProviderEntity();

        Booking booking = bookingRepository.findById(bookingId)
            .orElseThrow(BookingNotFoundException::new);

        if (!booking.isOwnedBy(provider)) {
            throw new BookingNotFoundException();
        }

        if (!booking.isAccepted()) {
            throw new InvalidBookingStateException(
                "Only accepted bookings can be completed."
            );
        }

        return booking;

    }

    private void validateCompletionTime(Booking booking) {

        if (LocalDateTime.now().isBefore(booking.getScheduledEnd())) {

            throw new InvalidBookingStateException(
                "A booking cannot be completed before its scheduled end time."
            );

        }

    }

    private Booking getPendingBookingForCurrentCustomer(Long bookingId) {

        Long userId = SecurityUtils.currentUserId();

        User customer = userRepository.findById(userId)
            .orElseThrow(UserNotFoundException::new);

        Booking booking = bookingRepository.findById(bookingId)
            .orElseThrow(BookingNotFoundException::new);

        if (!booking.isOwnedBy(customer)) {
            throw new BookingNotFoundException();
        }

        if (!booking.isPending()) {
            throw new InvalidBookingStateException(
                "Only pending bookings can be cancelled."
            );
        }

        return booking;

    }

    private Booking getPendingBookingForCurrentProvider(Long bookingId) {

        Provider currentProvider = providerService.getCurrentProviderEntity();

        Booking booking = bookingRepository.findById(bookingId)
            .orElseThrow(BookingNotFoundException::new);

        if (!booking.isOwnedBy(currentProvider)) {
            throw new BookingNotFoundException();
        }

        if (!booking.isPending()) {
            throw new InvalidBookingStateException();
        }

        return booking;

    }

    private void validateScheduledDate(Booking booking, BookingAcceptanceRequest request) {

        LocalDate scheduledDate = request.getScheduledStart().toLocalDate();

        if (!scheduledDate.equals(booking.getPreferredDate())) {

            throw new InvalidBookingScheduleException();

        }

    }

    private void validateScheduledTimes(BookingAcceptanceRequest request) {

        if (!request.getScheduledEnd().isAfter(request.getScheduledStart())) {

            throw new InvalidBookingScheduleException(
                "Scheduled end time must be after the start time."
            );

        }

    }

    private void validateTimeWindow(Booking booking, BookingAcceptanceRequest request) {

        LocalTime scheduledTime = request.getScheduledStart().toLocalTime();

        if (!booking.getPreferredTimeWindow().contains(scheduledTime)) {

            throw new InvalidBookingScheduleException(
                "Scheduled time falls outside the customer's preferred time window."
            );

        }

    }

    private void validateNoOverlappingBookings(Booking booking, BookingAcceptanceRequest request) {

        boolean overlap = bookingRepository.existsByProviderAndStatusAndScheduledStartLessThanAndScheduledEndGreaterThan(
                    booking.getProvider(),
                    BookingStatus.ACCEPTED,
                    request.getScheduledEnd(),
                    request.getScheduledStart()
                );

        if (overlap) {

            throw new ProviderScheduleConflictException(
                "You already have an accepted booking during this time."
            );

        }

    }

    private User getAuthenticatedCustomer() {

        Long userId = SecurityUtils.currentUserId();

        return userRepository.findById(userId)
            .orElseThrow(UserNotFoundException::new);

    }

    private ServiceOffering getActiveServiceOffering(Long id) {

        return serviceOfferingRepository
            .findByIdAndActiveTrue(id)
            .orElseThrow(ServiceOfferingNotFoundException::new);

    }

}
