package com.marketplace.auth.service;

import com.marketplace.auth.dto.SessionResponse;
import com.marketplace.auth.entity.UserSession;
import com.marketplace.auth.exception.InvalidRefreshTokenException;
import com.marketplace.auth.exception.SessionExpiredException;
import com.marketplace.auth.exception.SessionNotFoundException;
import com.marketplace.auth.exception.SessionRevokedException;
import com.marketplace.auth.mapper.AuthMapper;
import com.marketplace.auth.model.SessionCreationResult;
import com.marketplace.auth.model.SessionMetadata;
import com.marketplace.auth.repository.UserSessionRepository;
import com.marketplace.security.TokenHashService;
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

    private final TokenHashService tokenHashService;

    private final AuthMapper authMapper;


    public SessionCreationResult createSession(User user, SessionMetadata metadata) {

        String refreshToken = jwtService.generateRefreshToken();

        UserSession session =
            UserSession.builder()
                .user(user)
                .refreshToken(tokenHashService.hash(refreshToken))
                .expiresAt(LocalDateTime.now().plusDays(30))
                .lastUsedAt(LocalDateTime.now())
                .revoked(false)
                .deviceName(metadata.deviceName())
                .ipAddress(metadata.ipAddress())
                .userAgent(metadata.userAgent())
                .build();

        sessionRepository.save(session);


        return SessionCreationResult.builder()
            .session(session)
            .refreshToken(refreshToken)
            .build();
    }


    @Transactional
    public UserSession validateSession(String refreshToken) {
        String hashedToken = tokenHashService.hash(refreshToken);
        UserSession session = sessionRepository
                .findByRefreshToken(hashedToken)
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


    public void revokeSession(String refreshToken) {
        UserSession session = validateSession(refreshToken);
        session.setRevoked(true);
        sessionRepository.save(session);

    }



    public void revokeAllSessions(Long userId) {
        List<UserSession> sessions = sessionRepository.findAllByUserId(userId);
        sessions.forEach(session -> {
            session.setRevoked(true);
        });

        sessionRepository.saveAll(sessions);
    }

    @Transactional
    public SessionCreationResult rotateSession(UserSession currentSession) {
        currentSession.setRevoked(true);
        sessionRepository.save(currentSession);

        String newRefreshToken = jwtService.generateRefreshToken();

        UserSession newSession = UserSession.builder()
            .user(currentSession.getUser())
            .refreshToken(tokenHashService.hash(newRefreshToken))
            .revoked(false)
            .expiresAt(LocalDateTime.now().plusDays(30))
            .lastUsedAt(LocalDateTime.now())
            .deviceName(currentSession.getDeviceName())
            .ipAddress(currentSession.getIpAddress())
            .userAgent(currentSession.getUserAgent())
            .build();

        sessionRepository.save(newSession);

        return SessionCreationResult.builder()
            .session(newSession)
            .refreshToken(newRefreshToken)
            .build();
    }

    @Transactional
    public void logout(Long sessionId) {
        UserSession session = sessionRepository.findByIdAndRevokedFalse(sessionId)
                .orElseThrow(SessionNotFoundException::new);

        session.setRevoked(true);

        sessionRepository.save(session);
    }

    public List<SessionResponse> getActiveSessions(Long userId, Long currentSessionId) {

        return sessionRepository
            .findAllByUserIdAndRevokedFalseOrderByLastUsedAtDesc(userId)
            .stream()
            .map(session -> {
                SessionResponse response = authMapper.toSessionResponse(session);
                response.setCurrent(session.getId().equals(currentSessionId));
                return response;
            })
            .toList();
    }


}
