package com.kpi.social_network.post.dto;


import com.kpi.social_network.image.model.Image;
import com.kpi.social_network.users.model.User;
import lombok.*;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostListQueryResult {
    public UUID id;
    public String body;
    public long likeCount;
    public long dislikeCount;
    public long commentCount;
    public Date createdAt;
    public Image image;
    public User user;
}
