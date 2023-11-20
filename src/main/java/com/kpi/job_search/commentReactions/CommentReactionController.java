package com.kpi.job_search.commentReactions;

import com.kpi.job_search.commentReactions.dto.CommentReactionsDto;
import com.kpi.job_search.commentReactions.dto.ReceivedCommentReactionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.kpi.job_search.auth.TokenService.getUserId;

@RestController
@RequestMapping("/api/comments/{id}/reaction")
@RequiredArgsConstructor
public class CommentReactionController {

    private final CommentReactionService commentReactionService;

    @GetMapping
    public CommentReactionsDto getReactions(@PathVariable UUID id) {
        return commentReactionService.getReactions(id);
    }

    @PostMapping
    public void setReaction(@PathVariable UUID id,
                            @RequestBody ReceivedCommentReactionDto commentReactionDto) {
        commentReactionDto.setCommentId(id);
        commentReactionDto.setUserId(getUserId());
        commentReactionService.setReaction(commentReactionDto);
    }
}
