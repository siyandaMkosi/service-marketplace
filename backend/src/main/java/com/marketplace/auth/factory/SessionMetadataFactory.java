package com.marketplace.auth.factory;


import com.marketplace.auth.model.SessionMetadata;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;


@Component
public class SessionMetadataFactory {



    public SessionMetadata from(
        HttpServletRequest request
    ) {


        return new SessionMetadata(

            extractIp(request),

            request.getHeader(
                "User-Agent"
            ),

            extractDevice(request)

        );

    }



    private String extractIp(
        HttpServletRequest request
    ) {


        String forwarded =
            request.getHeader(
                "X-Forwarded-For"
            );


        if(forwarded != null) {

            return forwarded.split(",")[0];

        }


        return request.getRemoteAddr();

    }



    private String extractDevice(HttpServletRequest request) {

        String userAgent = request.getHeader("User-Agent");

        if (userAgent == null) {
            return "Unknown Device";
        }

        String browser = "Unknown Browser";
        String os = "Unknown OS";

        // Browser
        if (userAgent.contains("Chrome")) {
            browser = "Chrome";
        } else if (userAgent.contains("Firefox")) {
            browser = "Firefox";
        } else if (userAgent.contains("Safari") && !userAgent.contains("Chrome")) {
            browser = "Safari";
        } else if (userAgent.contains("Edg")) {
            browser = "Edge";
        }

        // Operating System
        if (userAgent.contains("Mac OS")) {
            os = "macOS";
        } else if (userAgent.contains("Windows")) {
            os = "Windows";
        } else if (userAgent.contains("Linux")) {
            os = "Linux";
        } else if (userAgent.contains("Android")) {
            os = "Android";
        } else if (userAgent.contains("iPhone") || userAgent.contains("iPad")) {
            os = "iOS";
        }

        return browser + " on " + os;
    }
}
