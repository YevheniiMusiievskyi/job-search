package com.kpi.social_network.post.dto;

import lombok.Data;
import java.util.UUID;

@Data
public class PostUpdatingDto {
    private UUID id;
    private String body;
    private UUID imageId;
}
