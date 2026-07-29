package com.marketplace.bookings.factory;

import com.marketplace.bookings.dto.request.BookingCreateRequest;
import com.marketplace.bookings.entity.Booking;
import com.marketplace.bookings.enums.BookingStatus;
import com.marketplace.bookings.enums.PaymentStatus;
import com.marketplace.provider.entity.Provider;
import com.marketplace.service.entity.ServiceOffering;
import com.marketplace.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class  BookingFactory {

    public Booking createPendingBooking(User customer, ServiceOffering serviceOffering, BookingCreateRequest request) {

        Provider provider = serviceOffering.getProvider();

        Booking.BookingBuilder builder = Booking.builder()
            .customer(customer)
            .provider(provider)
            .serviceOffering(serviceOffering);

        copyServiceSnapshot(builder, serviceOffering);

        builder.preferredDate(request.getPreferredDate())
            .preferredTimeWindow(request.getPreferredTimeWindow())
            .customerNotes(request.getCustomerNotes())
            .status(BookingStatus.PENDING)
            .providerConfirmedTime(false);

        return builder.build();

    }

    private void copyServiceSnapshot(Booking.BookingBuilder builder, ServiceOffering offering) {

        builder.serviceName(offering.getName())
            .serviceDescription(offering.getDescription())
            .agreedPrice(offering.getBasePrice())
            .estimatedDurationMinutes(offering.getEstimatedDurationMinutes());

    }

}
