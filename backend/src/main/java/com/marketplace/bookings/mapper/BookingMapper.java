package com.marketplace.bookings.mapper;

import com.marketplace.bookings.dto.response.BookingResponse;
import com.marketplace.bookings.entity.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookingMapper {

    @Mapping(
        target = "providerBusinessName",
        source = "provider.businessName"
    )
    @Mapping(
        target = "customerFullName",
        expression = "java(booking.getCustomer().getFirstName() + \" \" + booking.getCustomer().getLastName())"
    )
    BookingResponse toResponse(Booking booking);

}
