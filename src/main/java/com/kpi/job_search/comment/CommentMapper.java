package com.kpi.job_search.comment;

import com.kpi.job_search.comment.dto.CommentDetailsDto;
import com.kpi.job_search.comment.dto.CommentDetailsQueryResult;
import com.kpi.job_search.comment.dto.CommentSaveDto;
import com.kpi.job_search.comment.model.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CommentMapper {
    CommentMapper MAPPER = Mappers.getMapper(CommentMapper.class);

    @Mapping(source = "post.id", target = "postId")
    CommentDetailsDto commentToCommentDetailsDto(Comment comment);

    @Mapping(source = "postId", target = "post.id")
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Comment commentDetailsDtoToComment(CommentDetailsDto commentDetailsDto);

    @Mapping(source = "postId", target = "post.id")
    @Mapping(source = "userId", target = "user.id")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Comment commentSaveDtoToModel(CommentSaveDto commentDto);

    @Mapping(source = "post.id", target = "postId")
    CommentDetailsDto commentDetailsQueryToCommentDetails(CommentDetailsQueryResult commentQuery);
}
