package com.marketplace.service.mapper;

import com.marketplace.common.seed.ServiceCategoryDefinition;
import com.marketplace.service.entity.ServiceCategory;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ServiceCategoryMapper {

    ServiceCategory toEntity(ServiceCategoryDefinition definition);

}
