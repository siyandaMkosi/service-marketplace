package com.marketplace.marketplace.service.query;

import com.marketplace.marketplace.dto.response.MarketplaceServiceResponse;
import com.marketplace.marketplace.mapper.MarketplaceMapper;
import com.marketplace.service.exception.ServiceOfferingNotFoundException;
import com.marketplace.service.repository.ServiceOfferingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MarketplaceQueryService {

    private final ServiceOfferingRepository repository;
    private final MarketplaceMapper mapper;

    public List<MarketplaceServiceResponse> findAllServiceOfferings() {

        return repository
            .findAllByActiveTrueOrderByNameAsc()
            .stream()
            .map(mapper::toResponse)
            .toList();

    }

    public MarketplaceServiceResponse findServiceOfferingById(Long id) {

        return mapper.toResponse(

            repository.findById(id)
                .orElseThrow(ServiceOfferingNotFoundException::new)

        );

    }

}
