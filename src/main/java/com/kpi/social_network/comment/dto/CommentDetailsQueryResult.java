package com.kpi.social_network.comment.dto;

import com.kpi.social_network.post.model.Post;
import com.kpi.social_network.users.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDetailsQueryResult {
    private UUID id;
    private String body;
    private long likeCount;
    private long dislikeCount;
    private Date createdAt;
    private Date updatedAt;
    private User user;
    private Post post;
}
