package com.kpi.job_search.auth.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Set;
import java.util.UUID;

public class AuthUser extends User {
    public AuthUser(UUID id, String username, String password, Set<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.id = id;
    }

    @Getter @Setter private UUID id;
}
