package com.marketplace.service.facade;

import com.marketplace.service.dto.response.ServiceCategoryResponse;
import com.marketplace.service.service.ServiceCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceCategoryFacade {

    private final ServiceCategoryService service;

    public List<ServiceCategoryResponse> getAll() {

        return service.getAll();

    }

}
