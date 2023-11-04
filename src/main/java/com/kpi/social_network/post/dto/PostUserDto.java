package com.kpi.social_network.post.dto;

import com.kpi.social_network.image.dto.ImageDto;
import lombok.Data;
import java.util.UUID;

@Data
public class PostUserDto {
    private UUID id;
    private String username;
    private ImageDto image;
}
