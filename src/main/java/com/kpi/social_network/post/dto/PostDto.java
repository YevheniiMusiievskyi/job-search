package com.kpi.social_network.post.dto;

import com.kpi.social_network.image.dto.ImageDto;
import com.kpi.social_network.users.dto.UserDetailsDto;
import com.kpi.social_network.users.dto.UserShortDto;
import lombok.Data;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
public class PostDto {
    private UUID id;
    private String body;
    private ImageDto image;
    private UserDetailsDto user;
    private Date createdAt;
    private long likeCount;
    private long dislikeCount;
    private long commentCount;
}
