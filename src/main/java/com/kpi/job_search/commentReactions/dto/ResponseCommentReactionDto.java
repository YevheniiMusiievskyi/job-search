package com.kpi.job_search.commentReactions.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ResponseCommentReactionDto {
    private UUID id;
    private UUID commentId;
    private Boolean isLike;
    private UUID userId;
}
