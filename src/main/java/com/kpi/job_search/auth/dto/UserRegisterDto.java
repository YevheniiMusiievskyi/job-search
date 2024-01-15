package com.kpi.job_search.auth.dto;

import com.kpi.job_search.users.model.UserRole;
import lombok.Data;

@Data
public class UserRegisterDto {
    private String email;
    private String fullName;
    private String password;
    private UserRole role;
}