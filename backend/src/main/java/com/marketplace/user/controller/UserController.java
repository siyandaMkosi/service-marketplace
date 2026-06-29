package com.marketplace.user.controller;

import com.marketplace.common.response.ApiResponse;
import com.marketplace.common.response.ApiResponseBuilder;
import com.marketplace.user.dto.UserProfileResponse;
import com.marketplace.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<ApiResponse<UserProfileResponse>> getCurrentUser(
        Authentication authentication,
        HttpServletRequest servletRequest
    ) {
        UserProfileResponse response = userService.getCurrentUser(authentication);

        return ResponseEntity.ok(
            ApiResponseBuilder.success(
                "Current user retrieved successfully",
                response,
                servletRequest
            )
        );
    }
}
