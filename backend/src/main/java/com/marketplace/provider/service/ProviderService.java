package com.marketplace.provider.service;

import com.marketplace.provider.dto.request.ProviderRegistrationRequest;
import com.marketplace.provider.dto.response.ProviderResponse;
import com.marketplace.provider.entity.Provider;
import com.marketplace.provider.exception.ProviderAlreadyExistsException;
import com.marketplace.provider.exception.ProviderNotFoundException;
import com.marketplace.provider.mapper.ProviderMapper;
import com.marketplace.provider.repository.ProviderRepository;
import com.marketplace.security.CurrentUserService;
import com.marketplace.user.entity.User;
import com.marketplace.user.exception.UserNotFoundException;
import com.marketplace.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ProviderService {

    private final ProviderRepository providerRepository;

    private final UserRepository userRepository;

    private final CurrentUserService currentUserService;

    private final ProviderMapper providerMapper;

    @Transactional
    public ProviderResponse becomeProvider(
        ProviderRegistrationRequest request
    ) {

        User user = currentUserService.requireCurrentUser();

        Provider provider = providerMapper.toEntity(request);

        provider.assignUser(user);

        Provider savedProvider = providerRepository.save(provider);

        return providerMapper.toResponse(savedProvider);
    }

    @Transactional(readOnly = true)
    public ProviderResponse getCurrentProvider() {

        User currentUser = currentUserService.requireCurrentUser();

        Provider provider = providerRepository.findByUserId(currentUser.getId())
            .orElseThrow(ProviderNotFoundException::new);

        return providerMapper.toResponse(provider);
    }
}
