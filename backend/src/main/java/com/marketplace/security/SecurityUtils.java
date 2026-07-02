package com.marketplace.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public final class SecurityUtils {

    private SecurityUtils() {
    }

    public static CurrentUser getCurrentUser() {

        Authentication authentication =
            SecurityContextHolder.getContext()
                .getAuthentication();

        return (CurrentUser) authentication.getPrincipal();
    }

    public static Long currentUserId() {
        return getCurrentUser().getUserId();
    }

    public static Long currentSessionId() {
        return getCurrentUser().getSessionId();
    }
}
