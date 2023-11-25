package com.kpi.job_search.post.dto;

import com.kpi.job_search.image.dto.ImageDto;
import com.kpi.job_search.users.dto.UserDetailsDto;
import lombok.Data;

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
