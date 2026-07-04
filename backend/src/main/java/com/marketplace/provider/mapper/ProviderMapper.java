package com.marketplace.provider.mapper;

import com.marketplace.provider.dto.request.ProviderRegistrationRequest;
import com.marketplace.provider.dto.request.ProviderUpdateRequest;
import com.marketplace.provider.dto.response.ProviderResponse;
import com.marketplace.provider.entity.Provider;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ProviderMapper {

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "verified", ignore = true)
    @Mapping(target = "averageRating", ignore = true)
    @Mapping(target = "totalReviews", ignore = true)
    Provider toEntity(ProviderRegistrationRequest request);

    ProviderResponse toResponse(Provider provider);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProvider(ProviderUpdateRequest request, @MappingTarget Provider provider);

}
