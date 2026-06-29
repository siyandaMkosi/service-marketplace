package com.marketplace.user.dto;

import com.marketplace.common.enums.Role;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserProfileResponse {

    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private Role role;
    private boolean verified;
    private boolean active;
}
