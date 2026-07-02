package com.marketplace.auth.mapper;

import com.marketplace.auth.dto.SessionResponse;
import com.marketplace.auth.entity.UserSession;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SessionMapper {
    SessionResponse toResponse(UserSession session);
}
