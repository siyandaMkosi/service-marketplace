package com.marketplace.security.jwt;

import com.marketplace.auth.entity.UserSession;
import com.marketplace.common.enums.Role;
import com.marketplace.config.JwtProperties;
import com.marketplace.user.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final JwtProperties jwtProperties;

    private final SecureRandom secureRandom = new SecureRandom();

    public String generateAccessToken(User user, UserSession session) {

        return Jwts.builder()
            .subject(user.getEmail())
            .claim("userId", user.getId())
            .claim("role", user.getRole().name())
            .claim("sid", session.getId())
            .issuedAt(new Date())
            .expiration(
                new Date(
                    System.currentTimeMillis()
                        + jwtProperties.getExpiration()
                )
            )
            .signWith(getSigningKey())
            .compact();
    }

    public String generateRefreshToken() {

        byte[] randomBytes = new byte[64];

        secureRandom.nextBytes(randomBytes);

        return Base64.getUrlEncoder()
            .withoutPadding()
            .encodeToString(randomBytes);
    }

    public long getAccessTokenExpirySeconds() {
        return jwtProperties.getExpiration() / 1000;
    }

    public String extractUsername(String token) {

        return extractAllClaims(token).getSubject();
    }

    public boolean isTokenValid(String token) {

        try {
            Claims claims = extractAllClaims(token);

            return claims.getExpiration().toInstant()
                .isAfter(Instant.now());

        } catch (JwtException | IllegalArgumentException ex) {
            return false;
        }
    }
    private boolean isExpired(String token) {

        return extractAllClaims(token)
            .getExpiration()
            .before(new Date());
    }

    public Claims extractAllClaims(String token) {

        return Jwts.parser()
            .verifyWith(getSigningKey())
            .build()
            .parseSignedClaims(token)
            .getPayload();
    }

    private SecretKey getSigningKey() {

        return Keys.hmacShaKeyFor(
            jwtProperties.getSecretKey()
                .getBytes(StandardCharsets.UTF_8)
        );
    }

    public Long extractSessionId(String token) {

        Claims claims = extractAllClaims(token);

        return ((Number) claims.get("sid")).longValue();
    }

    public Long extractUserId(String token) {

        Claims claims = extractAllClaims(token);

        return ((Number) claims.get("userId")).longValue();
    }

    public Role extractRole(String token) {

        Claims claims = extractAllClaims(token);

        return Role.valueOf(
            claims.get("role", String.class)
        );
    }
}
