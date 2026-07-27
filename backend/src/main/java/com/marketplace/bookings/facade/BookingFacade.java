package com.marketplace.bookings.facade;

import com.marketplace.bookings.dto.request.BookingAcceptanceRequest;
import com.marketplace.bookings.dto.request.BookingCancellationRequest;
import com.marketplace.bookings.dto.request.BookingCreateRequest;
import com.marketplace.bookings.dto.request.BookingRejectionRequest;
import com.marketplace.bookings.dto.response.BookingResponse;
import com.marketplace.bookings.service.BookingService;
import com.marketplace.bookings.service.query.BookingQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingFacade {

    private final BookingService bookingService;
    private final BookingQueryService bookingQueryService;

    public BookingResponse requestBooking(BookingCreateRequest request) {
        return bookingService.requestBooking(request);
    }

    public List<BookingResponse> getPendingBookingsForCurrentProvider(){
        return  bookingQueryService.getPendingBookingsForCurrentProvider();
    }

    public BookingResponse acceptBooking(Long bookingId, BookingAcceptanceRequest request) {
        return bookingService.acceptBooking(bookingId, request);
    }

    public BookingResponse rejectBooking(Long bookingId, BookingRejectionRequest request) {

        return bookingService.rejectBooking(bookingId, request);

    }

    public BookingResponse cancelBooking(Long bookingId, BookingCancellationRequest request) {

        return bookingService.cancelBooking(bookingId, request);

    }

    public BookingResponse cancelAcceptedBooking(Long bookingId, BookingCancellationRequest request) {

        return bookingService.cancelAcceptedBooking(bookingId, request);

    }

    public BookingResponse completeBooking(Long bookingId) {

        return bookingService.completeBooking(bookingId);

    }

}
