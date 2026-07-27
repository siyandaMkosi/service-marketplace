package com.marketplace.bookings.facade;

import com.marketplace.bookings.dto.request.BookingCreateRequest;
import com.marketplace.bookings.dto.response.BookingResponse;
import com.marketplace.bookings.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookingFacade {

    private final BookingService bookingService;

    public BookingResponse requestBooking(
        BookingCreateRequest request
    ) {

        return bookingService.requestBooking(request);

    }

}
