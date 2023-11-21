package com.kpi.job_search.postReactions;

import com.kpi.job_search.postReactions.dto.PostReactionsDto;
import com.kpi.job_search.postReactions.dto.ReceivedPostReactionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.kpi.job_search.auth.TokenService.getUserId;

@RestController
@RequestMapping("/posts/{id}/reaction")
@RequiredArgsConstructor
public class PostReactionController {

	private final PostReactionService postsReactionService;

	@GetMapping
	public PostReactionsDto getReaction(@PathVariable UUID id) {
		return postsReactionService.getReactions(id);
	}

	@PostMapping
	public void setReaction(@PathVariable UUID id,
							@RequestBody ReceivedPostReactionDto postReaction) {
		postReaction.setPostId(id);
		postReaction.setUserId(getUserId());
		postsReactionService.setReaction(postReaction);
	}
}
