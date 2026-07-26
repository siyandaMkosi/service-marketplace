package com.marketplace.service.mapper;

import com.marketplace.common.seed.ServiceCategoryDefinition;
import com.marketplace.service.dto.response.ServiceCategoryResponse;
import com.marketplace.service.entity.ServiceCategory;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface ServiceCategoryMapper {

    ServiceCategory toEntity(ServiceCategoryDefinition definition);

    ServiceCategoryResponse toResponse(
        ServiceCategory category
    );

}
