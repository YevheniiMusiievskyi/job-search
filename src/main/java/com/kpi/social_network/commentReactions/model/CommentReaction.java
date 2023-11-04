package com.kpi.social_network.commentReactions.model;

import com.kpi.social_network.comment.model.Comment;
import com.kpi.social_network.db.BaseEntity;
import com.kpi.social_network.post.model.Post;
import com.kpi.social_network.users.model.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "comment_reactions",
		uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "comment_id"}))
public class CommentReaction extends BaseEntity {

	@Column(name = "is_like")
	private Boolean isLike;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "comment_id")
	private Comment comment;
}
