package com.marketplace.auth.model;

import com.marketplace.auth.entity.UserSession;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class SessionCreationResult {

    private UserSession session;

    private String refreshToken;

}
