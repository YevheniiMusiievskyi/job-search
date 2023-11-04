package com.kpi.social_network.users.dto;

import com.kpi.social_network.image.dto.ImageDto;
import lombok.Data;
import java.util.UUID;

@Data
public class UserDetailsDto {
    private UUID id;
    private String email;
    private String username;
    private ImageDto avatar;
}
