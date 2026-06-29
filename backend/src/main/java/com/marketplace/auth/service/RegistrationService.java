package com.marketplace.auth.service;

import com.marketplace.auth.dto.RegisterRequest;
import com.marketplace.auth.dto.RegisterResponse;
import com.marketplace.auth.mapper.AuthMapper;
import com.marketplace.exception.DuplicateResourceException;
import com.marketplace.user.entity.User;
import com.marketplace.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class RegistrationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public User register(RegisterRequest request) {

        if (repository.existsByEmail(request.getEmail().toLowerCase())) {
            throw new DuplicateResourceException("Email already exists.");
        }

        User user = User.builder()
            .firstName(request.getFirstName())
            .lastName(request.getLastName())
            .email(request.getEmail().toLowerCase())
            .password(passwordEncoder.encode(request.getPassword()))
            .role(request.getRole())
            .active(true)
            .verified(false)
            .build();

        return repository.save(user);
    }
}
