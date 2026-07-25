package com.marketplace.service.facade;

import com.marketplace.provider.facade.ProviderFacade;
import com.marketplace.service.dto.request.ServiceOfferingCreateRequest;
import com.marketplace.service.dto.request.ServiceOfferingUpdateRequest;
import com.marketplace.service.dto.response.ServiceOfferingResponse;
import com.marketplace.service.service.ServiceOfferingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceOfferingFacade {

    private final ProviderFacade providerFacade;
    private final ServiceOfferingService service;

    public ServiceOfferingResponse create(ServiceOfferingCreateRequest request) {
        return service.create(providerFacade.findCurrentProvider(), request);
    }

    public List<ServiceOfferingResponse> getAll() {
        return service.getAll(providerFacade.findCurrentProvider());
    }

    public ServiceOfferingResponse getById(Long id) {

        return service.getById(providerFacade.findCurrentProvider(), id);
    }

    public ServiceOfferingResponse update(Long id, ServiceOfferingUpdateRequest request) {

        return service.update(providerFacade.findCurrentProvider(), id, request);
    }

    public void deactivate(Long id) {
        service.deactivate(providerFacade.findCurrentProvider(), id);
    }

}
