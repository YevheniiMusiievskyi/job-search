package com.kpi.social_network.post;

import com.kpi.social_network.post.dto.PostDetailsQueryResult;
import com.kpi.social_network.post.model.Post;
import com.kpi.social_network.post.dto.PostListQueryResult;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PostsRepository extends JpaRepository<Post, UUID> {

    @Query("SELECT new com.kpi.social_network.post.dto.PostListQueryResult(p.id, p.body, " +
            "(SELECT COALESCE(SUM(CASE WHEN pr.isLike = TRUE THEN 1 ELSE 0 END), 0) FROM p.reactions pr WHERE pr.post = p), " +
            "(SELECT COALESCE(SUM(CASE WHEN pr.isLike = FALSE THEN 1 ELSE 0 END), 0) FROM p.reactions pr WHERE pr.post = p), " +
            "(SELECT COUNT(*) FROM Comment c WHERE c.post = p AND c.deleted = false), " +
            "p.createdAt, i, p.user) " +
            "FROM Post p " +
            "LEFT JOIN p.image i " +
            "WHERE (( cast(:userId as string) is null OR p.user.id = :userId) " +
            "AND p.deleted = false) " +
            "order by p.createdAt desc" )
    List<PostListQueryResult> findAllPosts(@Param("userId") UUID userId, Pageable pageable);

    @Query("SELECT new com.kpi.social_network.post.dto.PostDetailsQueryResult(p.id, p.body, " +
            "(SELECT COALESCE(SUM(CASE WHEN pr.isLike = TRUE THEN 1 ELSE 0 END), 0) FROM p.reactions pr WHERE pr.post = p), " +
            "(SELECT COALESCE(SUM(CASE WHEN pr.isLike = FALSE THEN 1 ELSE 0 END), 0) FROM p.reactions pr WHERE pr.post = p), " +
            "(SELECT COUNT(*) FROM Comment c WHERE c.post = p AND c.deleted = false), " +
            "p.createdAt, p.updatedAt, i, p.user) " +
            "FROM Post p " +
            "LEFT JOIN p.image i " +
            "WHERE p.id = :id " +
            "AND p.deleted = false")
    Optional<PostDetailsQueryResult> findPostById(@Param("id") UUID id);

    @Modifying
    @Transactional
    @Query("UPDATE Post p SET p.deleted = true WHERE p.id = :id")
    void softDeletePostById(@Param("id") UUID id);

    @Modifying
    @Transactional
    @Query("UPDATE Post p SET p.body = :body, " +
            "p.image = (SELECT i FROM Image i  WHERE i.id = :imageId)" +
            "WHERE p.id = :id")
    void update(@Param("id") UUID id, @Param("body") String body, @Param("imageId") UUID imageId);
}
