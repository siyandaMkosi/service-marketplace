# Entity Relationships

```mermaid
erDiagram

USER ||--o{ USER_SESSION : has

USER {

Long id

String firstName

String lastName

String email

String password

Role role

}

USER_SESSION {

Long id

String refreshToken

Boolean revoked

LocalDateTime expiresAt

LocalDateTime lastUsedAt

String deviceName

String ipAddress

String userAgent

}
```

## Description

A user can have multiple active sessions, allowing authentication from multiple devices simultaneously. Each session stores refresh token information and metadata to support secure session management.
