package com.marketplace.user.entity;

import com.marketplace.common.entity.BaseEntity;
import com.marketplace.common.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    private String firstName;

    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    private boolean verified;

    private boolean active;


    @PrePersist
    protected void onCreate() {
        active = true;
        verified = false;
    }

}
