package com.marketplace.auth.facade;

import com.marketplace.auth.dto.*;
import com.marketplace.auth.factory.SessionMetadataFactory;
import com.marketplace.auth.mapper.AuthMapper;
import com.marketplace.auth.model.AuthenticationResult;
import com.marketplace.auth.model.SessionMetadata;
import com.marketplace.auth.service.AuthenticationService;
import com.marketplace.auth.service.RegistrationService;
import com.marketplace.security.jwt.JwtService;
import com.marketplace.user.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthFacade {

    private final RegistrationService registrationService;

    private final AuthenticationService authenticationService;

    private final SessionMetadataFactory metadataFactory;

    private final AuthMapper authMapper;
    private final JwtService jwtService;

    public RegisterResponse register(RegisterRequest request) {

        User user = registrationService.register(request);

        return authMapper.toRegisterResponse(user);
    }

    public LoginResponse login(

        LoginRequest request,

        HttpServletRequest httpRequest

    ) {

        SessionMetadata metadata =
            metadataFactory.from(httpRequest);

        AuthenticationResult session =
            authenticationService.login(
                request,
                metadata
            );

        LoginResponse response =
            authMapper.toLoginResponse(session);

        return authMapper.toLoginResponse(session)
            .toBuilder()
            .tokenType("Bearer")
            .expiresIn(jwtService.getAccessTokenExpirySeconds())
            .build();

    }

    public LoginResponse refresh(RefreshTokenRequest request) {

        AuthenticationResult result =
            authenticationService.refresh(
                request.refreshToken()
            );


        return authMapper.toLoginResponse(result);
    }

}
