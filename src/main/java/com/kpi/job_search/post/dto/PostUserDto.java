package com.kpi.job_search.post.dto;

import com.kpi.job_search.image.dto.ImageDto;
import lombok.Data;
import java.util.UUID;

@Data
public class PostUserDto {
    private UUID id;
    private String username;
    private ImageDto image;
}
