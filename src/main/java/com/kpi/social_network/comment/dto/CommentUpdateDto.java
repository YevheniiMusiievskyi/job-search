package com.kpi.social_network.comment.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class CommentUpdateDto {
	private UUID id;
	private String body;
	private UUID postId;
}
