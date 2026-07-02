package com.marketplace.auth.repository;


import com.marketplace.auth.entity.UserSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface UserSessionRepository extends JpaRepository<UserSession, Long> {

    Optional<UserSession> findByRefreshToken(String hashedToken);

    List<UserSession> findAllByUserId(Long userId);

    Optional<UserSession> findByIdAndRevokedFalse(Long id);

    List<UserSession> findAllByUserIdAndRevokedFalseOrderByLastUsedAtDesc(
        Long userId
    );

}
