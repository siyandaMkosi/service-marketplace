package com.marketplace.service.config;

import com.marketplace.common.seed.DefaultServiceCategories;
import com.marketplace.common.seed.ServiceCategoryDefinition;
import com.marketplace.service.mapper.ServiceCategoryMapper;
import com.marketplace.service.repository.ServiceCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ServiceCategoryInitializer implements CommandLineRunner {

    private final ServiceCategoryRepository repository;
    private final ServiceCategoryMapper mapper;

    @Override
    public void run(String... args) {

        for (ServiceCategoryDefinition definition :
            DefaultServiceCategories.CATEGORIES) {

            if (!repository.existsByCode(definition.getCode())) {
                repository.save(mapper.toEntity(definition));
            }
        }
    }
}
