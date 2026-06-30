package com.marketplace.auth.service;

import com.marketplace.auth.entity.UserSession;
import com.marketplace.auth.exception.InvalidRefreshTokenException;
import com.marketplace.auth.exception.SessionExpiredException;
import com.marketplace.auth.exception.SessionRevokedException;
import com.marketplace.auth.model.SessionMetadata;
import com.marketplace.auth.repository.UserSessionRepository;
import com.marketplace.security.jwt.JwtService;
import com.marketplace.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class SessionService {


    private static final long REFRESH_TOKEN_EXPIRY_DAYS = 30;


    private final UserSessionRepository sessionRepository;

    private final JwtService jwtService;



    /**
     * Creates a new session after login
     */
    public UserSession createSession(User user, SessionMetadata metadata) {

        UserSession session =
            UserSession.builder()
                .user(user)
                .refreshToken(jwtService.generateRefreshToken())
                .expiresAt(LocalDateTime.now().plusDays(REFRESH_TOKEN_EXPIRY_DAYS))
                .lastUsedAt(LocalDateTime.now())
                .revoked(false)
                .deviceName(metadata.deviceName())
                .ipAddress(metadata.ipAddress())
                .userAgent(metadata.userAgent())
                .build();
        return sessionRepository.save(session);

    }




    /**
     * Validates a refresh token session
     */
    @Transactional
    public UserSession validateSession(String refreshToken) {
        UserSession session =
            sessionRepository
                .findByRefreshToken(refreshToken)
                .orElseThrow(InvalidRefreshTokenException::new
                );


        if(session.isRevoked()) {
            throw new SessionRevokedException();
        }

        if(session.isExpired()) {
            throw new SessionExpiredException();
        }

        session.setLastUsedAt(LocalDateTime.now());

        return session;

    }





    /**
     * Revoke a single device/session
     */
    public void revokeSession(String refreshToken) {

        UserSession session =
            validateSession(refreshToken);
        session.setRevoked(true);
        sessionRepository.save(session);

    }




    /**
     * Logout all devices
     */
    public void revokeAllSessions(Long userId) {

        List<UserSession> sessions =
            sessionRepository.findAllByUserId(userId);

        sessions.forEach(session -> {
            session.setRevoked(true);
        });

        sessionRepository.saveAll(sessions);
    }


}
