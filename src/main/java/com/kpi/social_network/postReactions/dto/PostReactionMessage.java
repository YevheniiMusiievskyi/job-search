package com.kpi.social_network.postReactions.dto;

import com.kpi.social_network.post.dto.PostShortDto;
import com.kpi.social_network.users.dto.UserShortDto;
import lombok.Data;
import java.util.UUID;

@Data
public class PostReactionMessage {
    private UUID id;
    private Boolean isLike;
    private PostShortDto post;
    private UserShortDto user;
}
