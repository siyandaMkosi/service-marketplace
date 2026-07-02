package com.marketplace.auth.mapper;

import com.marketplace.auth.dto.LoginResponse;
import com.marketplace.auth.dto.RegisterResponse;
import com.marketplace.auth.dto.SessionResponse;
import com.marketplace.auth.entity.UserSession;
import com.marketplace.auth.model.AuthenticationResult;
import com.marketplace.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuthMapper {

    @Mapping(source = "id", target = "userId")
    RegisterResponse toRegisterResponse(User user);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.firstName", target = "firstName")
    @Mapping(source = "user.lastName", target = "lastName")
    @Mapping(source = "user.email", target = "email")
    @Mapping(source = "user.role", target = "role")
    @Mapping(source = "accessToken", target = "accessToken")
    @Mapping(source = "refreshToken", target = "refreshToken")
    LoginResponse toLoginResponse(
        AuthenticationResult result);


    SessionResponse toSessionResponse(UserSession session);
}
