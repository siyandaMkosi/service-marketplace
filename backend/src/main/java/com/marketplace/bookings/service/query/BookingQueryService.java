package com.marketplace.bookings.service.query;

import com.marketplace.bookings.dto.response.BookingResponse;
import com.marketplace.bookings.enums.BookingStatus;
import com.marketplace.bookings.mapper.BookingMapper;
import com.marketplace.bookings.repository.BookingRepository;
import com.marketplace.provider.entity.Provider;
import com.marketplace.provider.repository.ProviderRepository;
import com.marketplace.provider.service.ProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingQueryService {

    private final ProviderService providerService;
    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;

    public List<BookingResponse> getPendingBookingsForCurrentProvider() {

        Provider provider = providerService.getCurrentProviderEntity();

        return bookingRepository.findAllByProviderAndStatusOrderByCreatedAtAsc(
                provider,
                BookingStatus.PENDING
            )
            .stream()
            .map(bookingMapper::toResponse)
            .toList();

    }
}
