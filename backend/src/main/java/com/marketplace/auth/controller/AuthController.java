package com.marketplace.auth.controller;

import com.marketplace.auth.dto.*;
import com.marketplace.auth.facade.AuthFacade;
import com.marketplace.common.response.ApiResponse;
import com.marketplace.common.response.ApiResponseBuilder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthFacade authFacade;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<RegisterResponse>> register(@Valid @RequestBody RegisterRequest request, HttpServletRequest servletRequest) {

        RegisterResponse response = authFacade.register(request);

        return ResponseEntity.status(HttpStatus.CREATED)
            .body(
                ApiResponseBuilder.success(
                    "User registered successfully",
                    response,
                    servletRequest
                )
            );
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(
        @Valid @RequestBody LoginRequest request,
        HttpServletRequest servletRequest
    ) {

        LoginResponse response =
            authFacade.login(request, servletRequest);

        return ResponseEntity.ok(
            ApiResponseBuilder.success(
                "Login successful",
                response,
                servletRequest
            )
        );
    }

    @PostMapping("/refresh")
    public ResponseEntity<ApiResponse<LoginResponse>> refresh(@Valid @RequestBody RefreshTokenRequest request, HttpServletRequest httpRequest) {

        LoginResponse response =
            authFacade.refresh(request);

        return ResponseEntity.ok(
            ApiResponseBuilder.success(
                "Token refreshed successfully",
                response,
                httpRequest
            )
        );
    }
}
