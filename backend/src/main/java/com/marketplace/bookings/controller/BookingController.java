package com.marketplace.bookings.controller;

import com.marketplace.bookings.dto.request.BookingCreateRequest;
import com.marketplace.bookings.dto.response.BookingResponse;
import com.marketplace.bookings.facade.BookingFacade;
import com.marketplace.common.response.ApiResponse;
import com.marketplace.common.response.ApiResponseBuilder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingFacade bookingFacade;

    @PostMapping
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<ApiResponse<BookingResponse>> requestBooking(@Valid @RequestBody BookingCreateRequest request, HttpServletRequest httpRequest) {

        BookingResponse response = bookingFacade.requestBooking(request);

        return ResponseEntity.ok(
            ApiResponseBuilder.success(
                "Booking request submitted successfully.",
                response,
                httpRequest
            )
        );

    }

}
