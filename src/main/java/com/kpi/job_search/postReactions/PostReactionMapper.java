package com.kpi.job_search.postReactions;

import com.kpi.job_search.post.PostMapper;
import com.kpi.job_search.postReactions.dto.ReceivedPostReactionDto;
import com.kpi.job_search.postReactions.dto.PostReactionMessage;
import com.kpi.job_search.postReactions.model.PostReaction;
import com.kpi.job_search.users.UserMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

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
