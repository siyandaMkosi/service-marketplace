package com.marketplace.bookings.service;

import com.marketplace.bookings.dto.request.BookingAcceptanceRequest;
import com.marketplace.bookings.dto.request.BookingCancellationRequest;
import com.marketplace.bookings.dto.request.BookingCreateRequest;
import com.marketplace.bookings.dto.request.BookingRejectionRequest;
import com.marketplace.bookings.dto.response.BookingResponse;
import com.marketplace.bookings.service.command.BookingCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingCommandService commandService;

    public BookingResponse requestBooking(BookingCreateRequest request) {

        return commandService.requestBooking(request);

    }

    public BookingResponse acceptBooking(Long bookingId, BookingAcceptanceRequest request) {

        return commandService.acceptBooking(bookingId, request);

    }

    public BookingResponse rejectBooking(Long bookingId, BookingRejectionRequest request) {

        return commandService.rejectBooking(bookingId, request);

    }

    public BookingResponse cancelBooking(Long bookingId, BookingCancellationRequest request) {

        return commandService.cancelBooking(bookingId, request);

    }

    public BookingResponse cancelAcceptedBooking(Long bookingId, BookingCancellationRequest request) {

        return commandService.cancelAcceptedBooking(bookingId, request);

    }

    public BookingResponse completeBooking(Long bookingId) {

        return commandService.completeBooking(bookingId);

    }

}
