package com.kpi.social_network.post.dto;

import com.kpi.social_network.users.dto.UserShortDto;
import lombok.Data;

import java.util.UUID;

@Data
public class PostShortDto {
	private UUID id;
	private String body;
	private UserShortDto user;
}
