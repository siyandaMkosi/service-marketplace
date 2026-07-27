package com.marketplace.bookings.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BookingAcceptanceRequest {

    @NotNull
    private LocalDateTime scheduledStart;

    @NotNull
    private LocalDateTime scheduledEnd;

    private String providerNotes;

}
