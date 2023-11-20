package com.kpi.job_search.comment;

import com.kpi.job_search.comment.dto.CommentDetailsQueryResult;
import com.kpi.job_search.comment.model.Comment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CommentRepository extends JpaRepository<Comment, UUID> {
    @Query("SELECT new com.kpi.job_search.comment.dto.CommentDetailsQueryResult(c.id, c.body, " +
            "(SELECT COALESCE(SUM(CASE WHEN cr.isLike = TRUE THEN 1 ELSE 0 END), 0) FROM c.reactions cr WHERE cr.comment = c), " +
            "(SELECT COALESCE(SUM(CASE WHEN cr.isLike = FALSE THEN 1 ELSE 0 END), 0) FROM c.reactions cr WHERE cr.comment = c), " +
            "c.createdAt, c.updatedAt, c.user, c.post) " +
            "FROM Comment c " +
            "WHERE c.post.id = :postId " +
            "AND c.deleted = false " +
            "ORDER BY c.createdAt asc")
    List<CommentDetailsQueryResult> findAllByPostId(@Param("postId") UUID postId, Pageable pageable);

    @Query("SELECT new com.kpi.job_search.comment.dto.CommentDetailsQueryResult(c.id, c.body, " +
            "(SELECT COALESCE(SUM(CASE WHEN cr.isLike = TRUE THEN 1 ELSE 0 END), 0) FROM c.reactions cr WHERE cr.comment = c), " +
            "(SELECT COALESCE(SUM(CASE WHEN cr.isLike = FALSE THEN 1 ELSE 0 END), 0) FROM c.reactions cr WHERE cr.comment = c), " +
            "c.createdAt, c.updatedAt, c.user, c.post) " +
            "FROM Comment c " +
            "WHERE c.id = :id " +
            "AND c.deleted = false")
    Optional<CommentDetailsQueryResult> findCommentById(@Param("id") UUID id);

    @Transactional
    @Modifying
    @Query("UPDATE Comment c SET c.deleted = true WHERE c.id = :id")
    void softDeleteById(@Param("id") UUID id);

    @Transactional
    @Modifying
    @Query("UPDATE Comment c SET c.body = :body WHERE c.id = :id")
    void update(@Param("id") UUID id, @Param("body") String body);
}
