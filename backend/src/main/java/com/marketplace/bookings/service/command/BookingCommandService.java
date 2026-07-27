package com.marketplace.bookings.service.command;


import com.marketplace.bookings.dto.request.BookingCreateRequest;
import com.marketplace.bookings.dto.response.BookingResponse;
import com.marketplace.bookings.entity.Booking;
import com.marketplace.bookings.factory.BookingFactory;
import com.marketplace.bookings.mapper.BookingMapper;
import com.marketplace.bookings.repository.BookingRepository;
import com.marketplace.security.SecurityUtils;
import com.marketplace.service.entity.ServiceOffering;
import com.marketplace.service.exception.ServiceOfferingNotFoundException;
import com.marketplace.service.repository.ServiceOfferingRepository;
import com.marketplace.user.entity.User;
import com.marketplace.user.exception.UserNotFoundException;
import com.marketplace.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class  BookingCommandService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final ServiceOfferingRepository serviceOfferingRepository;
    private final BookingFactory bookingFactory;
    private final BookingMapper bookingMapper;


    public BookingResponse requestBooking(BookingCreateRequest request) {

        User customer = getAuthenticatedCustomer();

        ServiceOffering serviceOffering = getActiveServiceOffering(request.getServiceOfferingId());

        Booking booking = bookingFactory.createPendingBooking(customer, serviceOffering, request);

        Booking savedBooking = bookingRepository.save(booking);

        return bookingMapper.toResponse(savedBooking);

    }

    private User getAuthenticatedCustomer() {

        Long userId = SecurityUtils.currentUserId();

        return userRepository.findById(userId)
            .orElseThrow(UserNotFoundException::new);

    }

    private ServiceOffering getActiveServiceOffering(Long id) {

        return serviceOfferingRepository
            .findByIdAndActiveTrue(id)
            .orElseThrow(ServiceOfferingNotFoundException::new);

    }

}
