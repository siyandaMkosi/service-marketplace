package com.marketplace.security;

import com.marketplace.common.enums.Role;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import java.security.Principal;
import java.util.Collection;

@Getter
@Builder
public class CurrentUser implements Principal {

    private Long userId;

    private Long sessionId;

    private String email;

    private Role role;

    @Override
    public String getName() {
        return email;
    }
}
