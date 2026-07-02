package com.marketplace.auth.service;

import com.marketplace.auth.dto.LoginRequest;
import com.marketplace.auth.dto.SessionResponse;
import com.marketplace.auth.entity.UserSession;
import com.marketplace.auth.model.AuthenticationResult;
import com.marketplace.auth.model.SessionCreationResult;
import com.marketplace.auth.model.SessionMetadata;
import com.marketplace.security.CurrentUserService;
import com.marketplace.security.jwt.JwtService;
import com.marketplace.user.entity.User;
import com.marketplace.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final SessionService sessionService;
    private final CurrentUserService currentUserService;

    @Transactional
    public AuthenticationResult login(LoginRequest request, SessionMetadata metadata) {
        User user = userRepository.findByEmail(request.getEmail().toLowerCase())
            .orElseThrow(() ->
                new BadCredentialsException(
                    "Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadCredentialsException(
                "Invalid email or password");
        }

        SessionCreationResult sessionResult = sessionService.createSession(user, metadata);

        String accessToken = jwtService.generateAccessToken(user, sessionResult.getSession());

        return AuthenticationResult.builder()
            .user(user)
            .session(sessionResult.getSession())
            .accessToken(accessToken)
            .refreshToken(sessionResult.getRefreshToken())
            .build();
    }

    @Transactional
    public AuthenticationResult refresh(String refreshToken) {
        UserSession session = sessionService.validateSession(refreshToken);

        SessionCreationResult rotatedSession = sessionService.rotateSession(session);

        User user = rotatedSession.getSession().getUser();

        String accessToken = jwtService.generateAccessToken(user,rotatedSession.getSession());

        return AuthenticationResult.builder()
            .user(user)
            .session(rotatedSession.getSession())
            .accessToken(accessToken)
            .refreshToken(rotatedSession.getRefreshToken())
            .build();
    }

    @Transactional
    public void logout() {
        sessionService.logout(currentUserService.getCurrentSessionId());
    }

    public List<SessionResponse> getActiveSessions() {
        return sessionService.getActiveSessions(currentUserService.getCurrentUserId(), currentUserService.getCurrentSessionId());
    }
}
