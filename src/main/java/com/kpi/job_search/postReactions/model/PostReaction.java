package com.kpi.job_search.postReactions.model;

import com.kpi.job_search.db.BaseEntity;
import com.kpi.job_search.post.model.Post;
import com.kpi.job_search.users.model.User;
import lombok.*;
import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode(callSuper=true)
@Table(name = "post_reactions",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "post_id"})}
)
public class PostReaction extends BaseEntity {

    @Column(name = "is_like", nullable = false)
    private Boolean isLike;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "post_id")
    private Post post;
}
