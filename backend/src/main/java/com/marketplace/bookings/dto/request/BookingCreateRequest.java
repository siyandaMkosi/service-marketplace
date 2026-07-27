package com.marketplace.bookings.dto.request;

import com.marketplace.bookings.enums.BookingTimeWindow;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class BookingCreateRequest {

    @NotNull
    private Long serviceOfferingId;

    @NotNull
    @Future
    private LocalDate preferredDate;

    @NotNull
    private BookingTimeWindow preferredTimeWindow;

    @NotBlank
    private String customerNotes;

}
