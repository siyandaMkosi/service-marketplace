package com.marketplace.service.service;

import com.marketplace.provider.entity.Provider;
import com.marketplace.service.dto.request.ServiceOfferingCreateRequest;
import com.marketplace.service.dto.request.ServiceOfferingUpdateRequest;
import com.marketplace.service.dto.response.ServiceOfferingResponse;
import com.marketplace.service.entity.ServiceCategory;
import com.marketplace.service.entity.ServiceOffering;
import com.marketplace.service.exception.DuplicateServiceOfferingException;
import com.marketplace.service.exception.ServiceCategoryNotFoundException;
import com.marketplace.service.exception.ServiceOfferingNotFoundException;
import com.marketplace.service.mapper.ServiceOfferingMapper;
import com.marketplace.service.repository.ServiceCategoryRepository;
import com.marketplace.service.repository.ServiceOfferingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ServiceOfferingService {

    private final ServiceOfferingRepository repository;
    private final ServiceCategoryRepository categoryRepository;
    private final ServiceOfferingMapper mapper;


    public ServiceOfferingResponse create(Provider provider, ServiceOfferingCreateRequest request) {

        if (repository.existsByProviderAndName(provider, request.getName())) {
            throw new DuplicateServiceOfferingException();
        }

        ServiceCategory category = categoryRepository.findById(request.getCategoryId())
            .orElseThrow(ServiceCategoryNotFoundException::new);

        ServiceOffering offering = mapper.toEntity(request);

        offering.setProvider(provider);
        offering.setCategory(category);

        repository.save(offering);

        return mapper.toResponse(offering);
    }

    @Transactional(readOnly = true)
    public List<ServiceOfferingResponse> getAll(Provider provider) {

        return repository.findAllByProvider(provider)
            .stream()
            .map(mapper::toResponse)
            .toList();
    }

    @Transactional(readOnly = true)
    public ServiceOfferingResponse getById(Provider provider, Long id) {

        return mapper.toResponse(
            getOwnedService(provider, id)
        );
    }


    public ServiceOfferingResponse update(Provider provider, Long id, ServiceOfferingUpdateRequest request) {

        ServiceOffering offering = getOwnedService(provider, id);

        if (request.getCategoryId() != null) {

            ServiceCategory category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(ServiceCategoryNotFoundException::new);

            offering.setCategory(category);
        }

        mapper.updateEntity(request, offering);

        return mapper.toResponse(offering);
    }


    public void deactivate(Provider provider, Long id) {

        ServiceOffering offering = getOwnedService(provider, id);
        offering.setActive(false);
    }

    private ServiceOffering getOwnedService(Provider provider, Long id) {

        return repository
            .findByIdAndProvider(id, provider)
            .orElseThrow(ServiceOfferingNotFoundException::new);
    }

}
