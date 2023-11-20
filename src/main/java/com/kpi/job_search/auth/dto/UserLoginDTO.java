package com.kpi.job_search.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserLoginDTO{
    private String email;
    private String password;
}
