package com.kpi.job_search.comment;

import java.util.List;
import java.util.UUID;

import com.kpi.job_search.auth.TokenService;
import com.kpi.job_search.comment.dto.CommentDetailsDto;
import com.kpi.job_search.comment.dto.CommentSaveDto;
import com.kpi.job_search.comment.dto.CommentUpdateDto;
import com.kpi.job_search.exceptions.ForbiddenException;
import com.kpi.job_search.exceptions.NotFoundException;
import com.kpi.job_search.users.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public List<CommentDetailsDto> getCommentsList(UUID postId, Integer page, Integer size) {
        var pageable = PageRequest.of(page, size);
        return commentRepository.findAllByPostId(postId, pageable)
                .stream()
                .map(CommentMapper.MAPPER::commentDetailsQueryToCommentDetails)
                .toList();
    }

    public CommentDetailsDto getCommentById(UUID id) {
        return commentRepository.findCommentById(id)
                .map(CommentMapper.MAPPER::commentDetailsQueryToCommentDetails)
                .orElseThrow();
    }

    public CommentDetailsDto create(CommentSaveDto commentDto) {
        var comment = CommentMapper.MAPPER.commentSaveDtoToModel(commentDto);
        var savedComment = commentRepository.save(comment);

        return CommentMapper.MAPPER.commentToCommentDetailsDto(savedComment);
    }

    public void softDeleteCommentById(UUID id) {
        checkAccess(id);
        commentRepository.softDeleteById(id);
    }

    public CommentDetailsDto updateComment(CommentUpdateDto commentUpdateDto) {
        checkAccess(commentUpdateDto.getId());
        commentRepository.update(commentUpdateDto.getId(), commentUpdateDto.getBody());
        return getCommentById(commentUpdateDto.getId());
    }

    private void checkAccess(UUID commentId) {
        var authorId = commentRepository.findById(commentId)
                .map(c -> c.getUser().getId())
                .orElseThrow(NotFoundException::new);

        if (!authorId.equals(TokenService.getUserId())) {
            throw new ForbiddenException();
        }
    }
}
