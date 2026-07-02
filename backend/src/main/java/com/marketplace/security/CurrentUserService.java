package com.marketplace.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class  CurrentUserService {

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
}
