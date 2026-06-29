package com.marketplace.auth.factory;

import com.marketplace.auth.model.SessionMetadata;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

@Component
public class SessionMetadataFactory {

    public SessionMetadata from(HttpServletRequest request) {

        return new SessionMetadata(

            extractIp(request),

            request.getHeader("User-Agent"),

            extractDevice(request)

        );
    }

    private String extractIp(HttpServletRequest request) {

        String forwarded = request.getHeader("X-Forwarded-For");

        if (forwarded != null && !forwarded.isBlank()) {
            return forwarded.split(",")[0];
        }

        return request.getRemoteAddr();
    }

    private String extractDevice(HttpServletRequest request) {

        String agent = request.getHeader("User-Agent");

        if (agent == null || agent.isBlank()) {
            return "Unknown Device";
        }

        return agent;
    }

}
