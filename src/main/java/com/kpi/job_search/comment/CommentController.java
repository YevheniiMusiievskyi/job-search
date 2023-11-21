package com.kpi.job_search.comment;

import com.kpi.job_search.comment.dto.CommentDetailsDto;
import com.kpi.job_search.comment.dto.CommentSaveDto;
import com.kpi.job_search.comment.dto.CommentUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import static com.kpi.job_search.auth.TokenService.getUserId;

@RestController
@RequestMapping("/{postId}/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final CommentMessagingService messagingService;

    @GetMapping
    public List<CommentDetailsDto> get(@PathVariable UUID postId,
                                       @RequestParam(defaultValue="0") Integer page,
                                       @RequestParam(defaultValue="10") Integer size) {
        return commentService.getCommentsList(postId, page, size);
    }

    @GetMapping("/{id}")
    public CommentDetailsDto get(@PathVariable UUID id) {
        return commentService.getCommentById(id);
    }

    @PostMapping
    public void post(@PathVariable UUID postId,
                                  @RequestBody CommentSaveDto commentDto) {
        commentDto.setUserId(getUserId());
        commentDto.setPostId(postId);
        var commentDetails = commentService.create(commentDto);
        messagingService.sendComment(commentDetails);
    }

    @PutMapping
    public void update(@PathVariable UUID postId,
                       @RequestBody CommentUpdateDto commentUpdateDto) {
        commentUpdateDto.setPostId(postId);
        var commentDetails = commentService.updateComment(commentUpdateDto);
        messagingService.sendCommentUpdate(commentDetails);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID postId,
                       @PathVariable UUID id) {
        commentService.softDeleteCommentById(id);
        messagingService.sendCommentDelete(postId, id);
    }
}
