package com.marketplace.provider.facade;

import com.marketplace.provider.dto.request.ProviderRegistrationRequest;
import com.marketplace.provider.dto.request.ProviderUpdateRequest;
import com.marketplace.provider.dto.response.ProviderResponse;
import com.marketplace.provider.service.ProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProviderFacade {

    private final ProviderService providerService;

    public ProviderResponse becomeProvider(ProviderRegistrationRequest request) {
        return providerService.becomeProvider(request);
    }

    public ProviderResponse getCurrentProvider() {
        return providerService.getCurrentProvider();
    }

    public ProviderResponse updateProvider(ProviderUpdateRequest request) {
        return providerService.updateProvider(request);
    }

}
