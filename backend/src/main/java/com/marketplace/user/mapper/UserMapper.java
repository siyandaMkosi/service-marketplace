package com.marketplace.user.mapper;

import com.marketplace.auth.dto.RegisterResponse;
import com.marketplace.user.dto.UserProfileResponse;
import com.marketplace.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "id", target = "userId")
    RegisterResponse toRegisterResponse(User user);

    @Mapping(source = "id", target = "userId")
    UserProfileResponse toUserProfileResponse(User user);
}
