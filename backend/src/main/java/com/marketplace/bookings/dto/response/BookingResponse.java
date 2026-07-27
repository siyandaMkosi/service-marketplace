package com.marketplace.bookings.dto.response;

import com.marketplace.bookings.enums.BookingStatus;
import com.marketplace.bookings.enums.BookingTimeWindow;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class BookingResponse {

    private Long id;

    private BookingStatus status;

    private LocalDate preferredDate;

    private BookingTimeWindow preferredTimeWindow;

    private String serviceName;

    private BigDecimal agreedPrice;

    private String providerBusinessName;

}
