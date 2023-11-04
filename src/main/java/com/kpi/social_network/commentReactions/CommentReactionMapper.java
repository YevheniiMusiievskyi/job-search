package com.kpi.social_network.commentReactions;

import com.kpi.social_network.commentReactions.dto.ReceivedCommentReactionDto;
import com.kpi.social_network.commentReactions.dto.ResponseCommentReactionDto;
import com.kpi.social_network.commentReactions.model.CommentReaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CommentReactionMapper {
    CommentReactionMapper MAPPER = Mappers.getMapper(CommentReactionMapper.class);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "comment.id", target = "commentId")
    ResponseCommentReactionDto reactionToCommentReactionDto(CommentReaction commentReaction);

    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "commentId", target = "comment.id")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    CommentReaction dtoToCommentReaction(ReceivedCommentReactionDto commentReactionDto);
}
