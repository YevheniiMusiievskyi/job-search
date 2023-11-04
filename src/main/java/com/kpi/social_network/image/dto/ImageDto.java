package com.kpi.social_network.image.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.UUID;

@Data
@Accessors(chain = true)
public class ImageDto {
    private UUID id;
    private String link;
}
