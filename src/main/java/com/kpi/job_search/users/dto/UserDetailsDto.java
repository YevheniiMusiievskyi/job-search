package com.kpi.job_search.users.dto;

import com.kpi.job_search.image.dto.ImageDto;
import lombok.Data;
import java.util.UUID;

@Data
public class UserDetailsDto {
    private UUID id;
    private String email;
    private String fullName;
    private ImageDto avatar;
}
