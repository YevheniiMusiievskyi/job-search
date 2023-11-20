package com.kpi.job_search.postReactions.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class PostReactionsDto {
	private UUID postId;
	private long likeCount;
	private long dislikeCount;
}
