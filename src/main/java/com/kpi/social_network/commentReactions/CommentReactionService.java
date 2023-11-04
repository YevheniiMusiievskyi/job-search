package com.kpi.social_network.commentReactions;

import com.kpi.social_network.commentReactions.dto.CommentReactionsDto;
import com.kpi.social_network.commentReactions.dto.ReceivedCommentReactionDto;
import com.kpi.social_network.commentReactions.dto.ResponseCommentReactionDto;
import com.kpi.social_network.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CommentReactionService {
    @Autowired
    CommentReactionRepository commentReactionRepository;

    public CommentReactionsDto getReactions(UUID commentId) {
        return commentReactionRepository.getCommentReactions(commentId)
                .orElseThrow(NotFoundException::new);
    }

    public Optional<ResponseCommentReactionDto> setReaction(ReceivedCommentReactionDto commentReactionDto) {
        var reaction = commentReactionRepository.getCommentReaction(commentReactionDto.getUserId(), commentReactionDto.getCommentId());

        if (reaction.isPresent()) {
            var react = reaction.get();
            if (react.getIsLike() == commentReactionDto.getIsLike()) {
                commentReactionRepository.deleteById(react.getId());
                return Optional.empty();
            } else {
                react.setIsLike(commentReactionDto.getIsLike());
                var result = commentReactionRepository.save(react);
                return Optional.of(CommentReactionMapper.MAPPER.reactionToCommentReactionDto(result));
            }
        } else {
            var postReaction = CommentReactionMapper.MAPPER.dtoToCommentReaction(commentReactionDto);
            var result = commentReactionRepository.save(postReaction);
            return Optional.of(CommentReactionMapper.MAPPER.reactionToCommentReactionDto(result));
        }
    }
}
