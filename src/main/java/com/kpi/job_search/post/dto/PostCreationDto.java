package com.kpi.job_search.post.dto;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class PostCreationDto {
    private String title;
    private String body;
    private List<String> skills;
    private UUID imageId;
    private UUID userId;
}
