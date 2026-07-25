package com.marketplace.service.mapper;

import com.marketplace.service.dto.request.ServiceOfferingCreateRequest;
import com.marketplace.service.dto.request.ServiceOfferingUpdateRequest;
import com.marketplace.service.dto.response.ServiceCategoryResponse;
import com.marketplace.service.dto.response.ServiceOfferingResponse;
import com.marketplace.service.entity.ServiceCategory;
import com.marketplace.service.entity.ServiceOffering;
import org.mapstruct.*;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface ServiceOfferingMapper {

    ServiceOffering toEntity(ServiceOfferingCreateRequest request);

    ServiceOfferingResponse toResponse(ServiceOffering serviceOffering);

    ServiceCategoryResponse toResponse(ServiceCategory category);

    @BeanMapping(
        nullValuePropertyMappingStrategy =
            NullValuePropertyMappingStrategy.IGNORE
    )
    void updateEntity(ServiceOfferingUpdateRequest request, @MappingTarget ServiceOffering entity);

}
