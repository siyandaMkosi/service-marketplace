package com.marketplace.security;

import com.marketplace.user.entity.User;
import com.marketplace.user.exception.UserNotFoundException;
import com.marketplace.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class  CurrentUserService {

    private final UserRepository userRepository;

    public CurrentUser getCurrentUser() {

        Authentication authentication =
            SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            throw new IllegalStateException("No authenticated user found.");
        }

        if (!(authentication.getPrincipal() instanceof CurrentUser currentUser)) {
            throw new IllegalStateException("Authenticated principal is invalid.");
        }

        return currentUser;
    }

    public Long getCurrentUserId() {
        return getCurrentUser().getUserId();
    }

    public Long getCurrentSessionId() {
        return getCurrentUser().getSessionId();
    }

    public String getCurrentUserEmail() {
        return getCurrentUser().getEmail();
    }

    public User requireCurrentUser() {Long userId = getCurrentUserId();

        return userRepository.findById(userId)
            .orElseThrow(UserNotFoundException::new);
    }
}
