package com.kpi.job_search.postReactions;

import com.kpi.job_search.postReactions.dto.PostReactionsDto;
import com.kpi.job_search.postReactions.model.PostReaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface PostReactionsRepository extends CrudRepository<PostReaction, UUID> {

	@Query("""
			SELECT new com.kpi.job_search.postReactions.dto.PostReactionsDto(
				p.id,
			    (SELECT COALESCE(SUM(CASE WHEN pr.isLike = true  THEN 1 ELSE 0 END), 0) FROM p.reactions pr WHERE pr.post = p),
			    (SELECT COALESCE(SUM(CASE WHEN pr.isLike = false THEN 1 ELSE 0 END), 0) FROM p.reactions pr WHERE pr.post = p))
			FROM Post p WHERE p.id = :postId
			""")
	Optional<PostReactionsDto> getReactions(UUID postId);

	@Query("SELECT r " +
			"FROM PostReaction r " +
			"WHERE r.user.id = :userId AND r.post.id = :postId ")
	Optional<PostReaction> getPostReaction(@Param("userId") UUID userId, @Param("postId") UUID postId);
}