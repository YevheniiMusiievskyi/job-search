package com.kpi.social_network.comment.model;


import com.kpi.social_network.commentReactions.model.CommentReaction;
import com.kpi.social_network.db.BaseEntity;
import com.kpi.social_network.post.model.Post;
import com.kpi.social_network.users.model.User;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Data
@EqualsAndHashCode(callSuper=true)
@Entity
@Table(name = "comments")
public class Comment extends BaseEntity {
    @Column(name = "body", columnDefinition="TEXT")
    private String body;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "post_id")
    private Post post;

    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CommentReaction> reactions = new ArrayList<>();

    @Column(nullable = false)
    @ColumnDefault("false")
    private boolean deleted;
}
