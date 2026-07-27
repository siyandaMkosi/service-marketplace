package com.marketplace.bookings.controller;

import com.marketplace.bookings.dto.request.BookingAcceptanceRequest;
import com.marketplace.bookings.dto.request.BookingCancellationRequest;
import com.marketplace.bookings.dto.request.BookingCreateRequest;
import com.marketplace.bookings.dto.request.BookingRejectionRequest;
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

import java.util.List;

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

    @GetMapping("/provider/pending")
    @PreAuthorize("hasRole('PROVIDER')")
    public ResponseEntity<ApiResponse<List<BookingResponse>>> getPendingBookings(HttpServletRequest request) {

        return ResponseEntity.ok(
            ApiResponseBuilder.success(
                "Pending booking requests retrieved successfully.",
                bookingFacade.getPendingBookingsForCurrentProvider(),
                request
            )
        );

    }

    @PutMapping("/{bookingId}/accept")
    @PreAuthorize("hasRole('PROVIDER')")
    public ResponseEntity<ApiResponse<BookingResponse>> acceptBooking(@PathVariable Long bookingId, @Valid @RequestBody BookingAcceptanceRequest request, HttpServletRequest httpRequest) {

        BookingResponse response = bookingFacade.acceptBooking(bookingId, request);

        return ResponseEntity.ok(
            ApiResponseBuilder.success(
                "Booking accepted successfully.",
                response,
                httpRequest
            )
        );

    }

    @PutMapping("/{bookingId}/reject")
    @PreAuthorize("hasRole('PROVIDER')")
    public ResponseEntity<ApiResponse<BookingResponse>> rejectBooking(@PathVariable Long bookingId, @Valid @RequestBody BookingRejectionRequest request, HttpServletRequest httpRequest) {

        BookingResponse response = bookingFacade.rejectBooking(bookingId, request);

        return ResponseEntity.ok(
            ApiResponseBuilder.success(
                "Booking rejected successfully.",
                response,
                httpRequest
            )
        );

    }

    @PutMapping("/{bookingId}/cancel")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<ApiResponse<BookingResponse>> cancelBooking(@PathVariable Long bookingId, @Valid @RequestBody BookingCancellationRequest request, HttpServletRequest httpRequest) {

        BookingResponse response = bookingFacade.cancelBooking(bookingId, request);

        return ResponseEntity.ok(
            ApiResponseBuilder.success(
                "Booking cancelled successfully.",
                response,
                httpRequest
            )
        );

    }

    @PutMapping("/{bookingId}/complete")
    @PreAuthorize("hasRole('PROVIDER')")
    public ResponseEntity<ApiResponse<BookingResponse>> completeBooking(@PathVariable Long bookingId, HttpServletRequest httpRequest) {

        BookingResponse response = bookingFacade.completeBooking(bookingId);

        return ResponseEntity.ok(
            ApiResponseBuilder.success(
                "Booking completed successfully.",
                response,
                httpRequest
            )
        );

    }

    @PutMapping("/{bookingId}/provider-cancel")
    @PreAuthorize("hasRole('PROVIDER')")
    public ResponseEntity<ApiResponse<BookingResponse>> cancelAcceptedBooking(@PathVariable Long bookingId, @Valid @RequestBody BookingCancellationRequest request, HttpServletRequest httpRequest) {

        BookingResponse response = bookingFacade.cancelAcceptedBooking(bookingId, request);

        return ResponseEntity.ok(
            ApiResponseBuilder.success(
                "Booking cancelled successfully.",
                response,
                httpRequest
            )
        );

    }

}
