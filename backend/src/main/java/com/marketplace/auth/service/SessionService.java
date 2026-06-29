package com.marketplace.auth.service;

import com.marketplace.auth.entity.UserSession;
import com.marketplace.auth.model.SessionMetadata;
import com.marketplace.auth.repository.UserSessionRepository;
import com.marketplace.security.jwt.JwtService;
import com.marketplace.user.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class SessionService {

    private static final long REFRESH_TOKEN_EXPIRY_DAYS = 30;

    private final UserSessionRepository repository;
    private final JwtService jwtService;

    public UserSession createSession(
        User user,
        SessionMetadata metadata
    ) {

        UserSession session = UserSession.builder()

            .user(user)

            .refreshToken(jwtService.generateRefreshToken())

            .expiresAt(LocalDateTime.now().plusDays(30))

            .lastUsedAt(LocalDateTime.now())

            .revoked(false)

            .ipAddress(metadata.ipAddress())

            .userAgent(metadata.userAgent())

            .deviceName(metadata.deviceName())

            .build();

        return repository.save(session);
    }

}
