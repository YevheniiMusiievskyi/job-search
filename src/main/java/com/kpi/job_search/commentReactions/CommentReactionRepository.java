package com.kpi.job_search.commentReactions;

import com.kpi.job_search.commentReactions.dto.CommentReactionsDto;
import com.kpi.job_search.commentReactions.model.CommentReaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface CommentReactionRepository extends CrudRepository<CommentReaction, UUID> {

    @Query("""
            SELECT new com.kpi.job_search.commentReactions.dto.CommentReactionsDto(
            	c.id,
            	(SELECT COALESCE(SUM(CASE WHEN cr.isLike = true  THEN 1 ELSE 0 END), 0) FROM c.reactions cr WHERE cr.comment = c),
            	(SELECT COALESCE(SUM(CASE WHEN cr.isLike = false THEN 1 ELSE 0 END), 0) FROM c.reactions cr WHERE cr.comment = c))
            FROM Comment c WHERE c.id = :commentId
            """)
    Optional<CommentReactionsDto> getCommentReactions(UUID commentId);

    @Query("SELECT r FROM CommentReaction r " +
            "WHERE r.user.id = :userId AND r.comment.id = :commentId")
    Optional<CommentReaction> getCommentReaction(@Param("userId") UUID userId, @Param("commentId") UUID commentId);
}
