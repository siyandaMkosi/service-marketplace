package com.marketplace.bookings.service;

import com.marketplace.bookings.dto.request.BookingCreateRequest;
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

}
