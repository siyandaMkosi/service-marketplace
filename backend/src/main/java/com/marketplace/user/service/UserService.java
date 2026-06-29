package com.marketplace.user.service;

import com.marketplace.user.dto.UserProfileResponse;
import com.marketplace.user.entity.User;
import com.marketplace.user.mapper.UserMapper;
import com.marketplace.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserProfileResponse getCurrentUser(Authentication authentication) {
        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Authenticated user not found"));

        return userMapper.toUserProfileResponse(user);
    }
}
