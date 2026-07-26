package com.marketplace.marketplace.service;

import com.marketplace.marketplace.dto.response.MarketplaceServiceResponse;
import com.marketplace.marketplace.mapper.MarketplaceMapper;
import com.marketplace.marketplace.service.query.MarketplaceQueryService;
import com.marketplace.service.repository.ServiceOfferingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MarketplaceService {

    private final MarketplaceQueryService queryService;

    public List<MarketplaceServiceResponse> findAllServiceOfferings() {

        return queryService.findAllServiceOfferings();

    }

    public MarketplaceServiceResponse findServiceOfferingById(Long id) {

        return queryService.findServiceOfferingById(id);

    }

}
