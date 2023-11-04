package com.kpi.social_network.auth.dto;


import com.kpi.social_network.users.dto.UserDetailsDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthUserDTO {
    private String token;
    private UserDetailsDto user;
}