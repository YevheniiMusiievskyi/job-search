package com.kpi.job_search.post.dto;

import com.kpi.job_search.users.dto.UserShortDto;
import lombok.Data;

import java.util.UUID;

@Data
public class PostShortDto {
	private UUID id;
	private String body;
	private UserShortDto user;
}
