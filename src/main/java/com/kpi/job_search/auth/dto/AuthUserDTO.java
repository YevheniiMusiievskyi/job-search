package com.kpi.job_search.auth.dto;


import com.kpi.job_search.users.dto.UserDetailsDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthUserDTO {
    private String token;
    private UserDetailsDto user;
}