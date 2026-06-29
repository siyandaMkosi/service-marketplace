package com.marketplace.auth.service;

import com.marketplace.auth.dto.LoginRequest;
import com.marketplace.auth.entity.UserSession;
import com.marketplace.auth.model.AuthenticationResult;
import com.marketplace.auth.model.SessionMetadata;
import com.marketplace.security.jwt.JwtService;
import com.marketplace.user.entity.User;
import com.marketplace.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final SessionService sessionService;

    @Transactional
    public AuthenticationResult login(

        LoginRequest request,

        SessionMetadata metadata

    ) {

        User user = userRepository.findByEmail(
                request.getEmail().toLowerCase())
            .orElseThrow(() ->
                new BadCredentialsException(
                    "Invalid email or password"));

        if (!passwordEncoder.matches(
            request.getPassword(),
            user.getPassword())) {

            throw new BadCredentialsException(
                "Invalid email or password");
        }

        String accessToken =
            jwtService.generateAccessToken(user);

        UserSession session =
            sessionService.createSession(user, metadata);

        return AuthenticationResult.builder()

            .user(user)

            .session(session)

            .accessToken(accessToken)

            .build();
    }
}
