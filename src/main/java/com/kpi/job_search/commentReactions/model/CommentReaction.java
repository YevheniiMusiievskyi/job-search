package com.kpi.job_search.commentReactions.model;

import com.kpi.job_search.comment.model.Comment;
import com.kpi.job_search.db.BaseEntity;
import com.kpi.job_search.users.model.User;
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
