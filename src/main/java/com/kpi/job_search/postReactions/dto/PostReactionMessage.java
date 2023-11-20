package com.kpi.job_search.postReactions.dto;

import com.kpi.job_search.post.dto.PostShortDto;
import com.kpi.job_search.users.dto.UserShortDto;
import lombok.Data;
import java.util.UUID;

@Data
public class PostReactionMessage {
    private UUID id;
    private Boolean isLike;
    private PostShortDto post;
    private UserShortDto user;
}
