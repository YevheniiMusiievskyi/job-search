package com.kpi.job_search.post.dto;


import com.kpi.job_search.image.model.Image;
import com.kpi.job_search.users.model.User;
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
