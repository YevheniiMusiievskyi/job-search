package com.kpi.job_search.users.model;

import lombok.Getter;

@Getter
public enum UserRole {
    USER,
    RECRUITER;

    private final String authority;

    UserRole() {
        this.authority = "ROLE_" + name();
    }
}
