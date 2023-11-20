package com.kpi.job_search.post;

import com.kpi.job_search.image.ImageMapper;
import com.kpi.job_search.post.dto.*;
import com.kpi.job_search.post.model.Post;
import com.kpi.job_search.users.UserMapper;
import com.kpi.job_search.users.model.User;
import org.mapstruct.*;


@Mapper(uses = { ImageMapper.class, UserMapper.class}, componentModel = "spring")
public abstract class PostMapper {
    public abstract PostDto postToPostDetailsDto(PostDetailsQueryResult post);

    @Mapping(source = "imageId", target = "image.id", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
    @Mapping(source = "userId", target = "user.id")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "comments", ignore = true)
    @Mapping(target = "reactions", ignore = true)
    public abstract Post postCreationDtoToPost(PostCreationDto postDetailsDto);

    @AfterMapping
    protected Post doAfterMapping(@MappingTarget Post entity) {
        if (entity != null && entity.getImage().getId() == null) {
            entity.setImage(null);
        }
        return entity;
    }

    public abstract PostDto postListToPostDto(PostListQueryResult model);

    @Mapping(source = "avatar", target = "image")
    public abstract PostUserDto postUserToPostUserDto(User model);

    @Mapping(target = "likeCount", ignore = true)
    @Mapping(target = "dislikeCount", ignore = true)
    @Mapping(target = "commentCount", expression = "java(post.getComments().size())")
    public abstract PostDto postToPostDetailsDto(Post post);

    @AfterMapping
    protected void afterPostToPostDetailsDto(Post post, @MappingTarget PostDto postDto) {
        post.getReactions()
                .forEach(r -> {
                    if (r.getIsLike()) {
                        postDto.setLikeCount(postDto.getLikeCount() + 1);
                    } else {
                        postDto.setDislikeCount(postDto.getLikeCount() + 1);
                    }
                });
    }

    public abstract PostShortDto postToPostShortDto(Post post);
}
