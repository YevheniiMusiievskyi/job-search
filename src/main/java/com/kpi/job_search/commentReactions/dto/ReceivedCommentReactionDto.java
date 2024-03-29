package com.kpi.job_search.commentReactions.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ReceivedCommentReactionDto {
    private UUID commentId;
    private UUID userId;
    private Boolean isLike;
}
