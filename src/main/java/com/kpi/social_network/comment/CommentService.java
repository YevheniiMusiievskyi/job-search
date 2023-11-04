package com.kpi.social_network.comment;

import java.util.List;
import java.util.UUID;

import com.kpi.social_network.auth.TokenService;
import com.kpi.social_network.comment.dto.CommentDetailsDto;
import com.kpi.social_network.comment.dto.CommentSaveDto;
import com.kpi.social_network.comment.dto.CommentUpdateDto;
import com.kpi.social_network.exceptions.ForbiddenException;
import com.kpi.social_network.exceptions.NotFoundException;
import com.kpi.social_network.users.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final User currentUser;

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

        var commentDetails = CommentMapper.MAPPER.commentToCommentDetailsDto(savedComment);
        commentDetails.getUser().setUsername(currentUser.getUsername());

        return commentDetails;
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
