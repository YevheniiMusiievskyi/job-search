package com.kpi.job_search.users.dto;

import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserShortDto {
    private UUID id;
    private String username;
}
