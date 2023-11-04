package com.kpi.social_network.commentReactions.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class CommentReactionsDto {
	private UUID commentId;
	private long likeCount;
	private long dislikeCount;
}
