package com.marketplace.marketplace.facade;

import com.marketplace.marketplace.dto.response.MarketplaceServiceResponse;
import com.marketplace.marketplace.service.MarketplaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MarketplaceFacade {

    private final MarketplaceService marketplaceService;

    public List<MarketplaceServiceResponse> findAllServiceOfferings() {

        return marketplaceService.findAllServiceOfferings();

    }

    public MarketplaceServiceResponse findServiceOfferingById(Long id) {

        return marketplaceService.findServiceOfferingById(id);

    }

}
