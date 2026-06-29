package com.marketplace.auth.dto;

import com.marketplace.common.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterResponse {

    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private Role role;
}
