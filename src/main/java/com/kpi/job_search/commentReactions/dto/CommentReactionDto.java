package com.kpi.job_search.commentReactions.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class CommentReactionDto {
    private UUID id;
    private Boolean isLike;
}
