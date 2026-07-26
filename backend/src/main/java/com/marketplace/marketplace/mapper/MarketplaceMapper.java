package com.marketplace.marketplace.mapper;

import com.marketplace.marketplace.dto.response.MarketplaceServiceResponse;
import com.marketplace.service.entity.ServiceOffering;
import com.marketplace.service.mapper.ServiceCategoryMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    uses = ServiceCategoryMapper.class
)
public interface MarketplaceMapper {

    @Mapping(target = "providerId", source = "provider.id")
    @Mapping(target = "businessName", source = "provider.businessName")
    MarketplaceServiceResponse toResponse(ServiceOffering offering);

}
