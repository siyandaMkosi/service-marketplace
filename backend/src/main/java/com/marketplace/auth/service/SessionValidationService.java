package com.marketplace.auth.service;

import com.marketplace.auth.entity.UserSession;
import com.marketplace.auth.exception.SessionExpiredException;
import com.marketplace.auth.exception.SessionNotFoundException;
import com.marketplace.auth.exception.SessionRevokedException;
import com.marketplace.auth.repository.UserSessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SessionValidationService {

    private final UserSessionRepository sessionRepository;

    @Transactional
    public UserSession validate(Long sessionId) {

        UserSession session = sessionRepository.findById(sessionId)
            .orElseThrow(() -> new SessionNotFoundException());

        if (session.isRevoked()) {
            throw new SessionRevokedException();
        }

        if (session.isExpired()) {
            throw new SessionExpiredException();
        }

        session.setLastUsedAt(LocalDateTime.now());

        return session;
    }
}
