package com.kpi.job_search.post.dto;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class PostCreationDto {
    private String body;
    private UUID imageId;
    private UUID userId;
}
