package com.marketplace.auth.dto;

import com.marketplace.common.enums.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder(toBuilder = true)
@Getter
public class LoginResponse {

    private Long userId;

    private String firstName;

    private String lastName;

    private String email;

    private Role role;

    private String accessToken;

    private String refreshToken;

    private String tokenType;

    private long expiresIn;

}
