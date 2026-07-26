package com.marketplace.service.service;

import com.marketplace.service.dto.response.ServiceCategoryResponse;
import com.marketplace.service.mapper.ServiceCategoryMapper;
import com.marketplace.service.repository.ServiceCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ServiceCategoryService {
    private final ServiceCategoryRepository repository;
    private final ServiceCategoryMapper mapper;


    public List<ServiceCategoryResponse> getAll() {

        return repository
            .findAllByActiveTrueOrderByNameAsc()
            .stream()
            .map(mapper::toResponse)
            .toList();

    }
}
