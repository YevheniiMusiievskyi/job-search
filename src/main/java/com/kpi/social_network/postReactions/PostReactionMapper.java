package com.kpi.social_network.postReactions;

import com.kpi.social_network.post.PostMapper;
import com.kpi.social_network.postReactions.dto.ReceivedPostReactionDto;
import com.kpi.social_network.postReactions.dto.PostReactionMessage;
import com.kpi.social_network.postReactions.model.PostReaction;
import com.kpi.social_network.users.UserMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {PostMapper.class, UserMapper.class})
public interface PostReactionMapper {

	PostReactionMessage reactionToPostReactionMessage(PostReaction postReaction);

    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "postId", target = "post.id")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    PostReaction dtoToPostReaction(ReceivedPostReactionDto postReactionDto);
}
