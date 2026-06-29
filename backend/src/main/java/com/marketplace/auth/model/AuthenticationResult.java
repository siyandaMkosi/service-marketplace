package com.marketplace.auth.model;

import com.marketplace.auth.entity.UserSession;
import com.marketplace.user.entity.User;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AuthenticationResult {

    private User user;

    private UserSession session;

    private String accessToken;

}
