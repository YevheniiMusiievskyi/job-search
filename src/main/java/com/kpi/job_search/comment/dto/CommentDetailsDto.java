package com.kpi.job_search.comment.dto;

import com.kpi.job_search.users.dto.UserDetailsDto;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class CommentDetailsDto {
    private UUID id;
    private String body;
    private UserDetailsDto user;
    private UUID postId;
    private Date createdAt;
    private long likeCount;
    private long dislikeCount;
}
