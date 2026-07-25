package com.marketplace.provider.service;

import com.marketplace.provider.dto.request.ProviderRegistrationRequest;
import com.marketplace.provider.dto.request.ProviderUpdateRequest;
import com.marketplace.provider.dto.response.ProviderResponse;
import com.marketplace.provider.entity.Provider;
import com.marketplace.provider.exception.ProviderNotFoundException;
import com.marketplace.provider.mapper.ProviderMapper;
import com.marketplace.provider.repository.ProviderRepository;
import com.marketplace.security.CurrentUserService;
import com.marketplace.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ProviderService {

    private final ProviderRepository providerRepository;

    private final CurrentUserService currentUserService;

    private final ProviderMapper providerMapper;

    @Transactional
    public ProviderResponse becomeProvider(ProviderRegistrationRequest request) {
        User user = currentUserService.requireCurrentUser();

        Provider provider = providerMapper.toEntity(request);
        provider.assignUser(user);

        Provider savedProvider = providerRepository.save(provider);
        return providerMapper.toResponse(savedProvider);
    }

    @Transactional(readOnly = true)
    public ProviderResponse getCurrentProvider() {
        return providerMapper.toResponse(requireCurrentProvider());
    }

    @Transactional
    public ProviderResponse updateProvider(ProviderUpdateRequest request) {
        Provider provider = requireCurrentProvider();
        providerMapper.updateProvider(request, provider);
        Provider updatedProvider = providerRepository.save(provider);

        return providerMapper.toResponse(updatedProvider);
    }

    private Provider requireCurrentProvider() {
        User currentUser = currentUserService.requireCurrentUser();
        return providerRepository.findByUserId(currentUser.getId())
            .orElseThrow(ProviderNotFoundException::new);
    }

    @Transactional(readOnly = true)
    public Provider findCurrentProvider() {

        Long currentUserId = currentUserService.getCurrentUserId();

        return providerRepository
            .findByUserId(currentUserId)
            .orElseThrow(ProviderNotFoundException::new);
    }
}
