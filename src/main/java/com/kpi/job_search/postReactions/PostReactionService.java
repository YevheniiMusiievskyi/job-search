package com.kpi.job_search.postReactions;

import com.kpi.job_search.exceptions.NotFoundException;
import com.kpi.job_search.postReactions.dto.PostReactionMessage;
import com.kpi.job_search.postReactions.dto.PostReactionsDto;
import com.kpi.job_search.postReactions.dto.ReceivedPostReactionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostReactionService {

    private final PostReactionsRepository postReactionsRepository;
    private final PostReactionMapper postReactionMapper;

    public PostReactionsDto getReactions(UUID id) {
        return postReactionsRepository.getReactions(id)
                .orElseThrow(NotFoundException::new);
    }

    public Optional<PostReactionMessage> setReaction(ReceivedPostReactionDto postReactionDto) {
        var reactionOptional = postReactionsRepository.getPostReaction(postReactionDto.getUserId(), postReactionDto.getPostId());

        if (reactionOptional.isPresent()) {
            var reaction = reactionOptional.get();
            if (reaction.getIsLike() == postReactionDto.getIsLike()) {
                postReactionsRepository.deleteById(reaction.getId());
                return Optional.empty();
            } else {
                reaction.setIsLike(postReactionDto.getIsLike());
                var result = postReactionsRepository.save(reaction);
                return Optional.of(postReactionMapper.reactionToPostReactionMessage(result));
            }
        } else {
            var postReaction = postReactionMapper.dtoToPostReaction(postReactionDto);
            var result = postReactionsRepository.save(postReaction);
            return Optional.of(postReactionMapper.reactionToPostReactionMessage(result));
        }
    }
}
