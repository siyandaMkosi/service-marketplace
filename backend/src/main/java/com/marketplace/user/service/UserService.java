package com.marketplace.user.service;

import com.marketplace.security.CurrentUserService;
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
    private final CurrentUserService currentUserService;

    public UserProfileResponse getCurrentUser() {

        User user = currentUserService.requireCurrentUser();
        return userMapper.toUserProfileResponse(user);
    }
}
