package com.kpi.social_network.post;

import com.kpi.social_network.auth.TokenService;
import com.kpi.social_network.exceptions.ForbiddenException;
import com.kpi.social_network.exceptions.NotFoundException;
import com.kpi.social_network.post.dto.*;
import com.kpi.social_network.post.model.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostsService {

    private final PostsRepository postsRepository;
    private final PostMapper postMapper;

    public List<PostDto> getAllPosts(Integer page, Integer size, UUID userId) {
        var pageable = PageRequest.of(page, size);
        return postsRepository
                .findAllPosts(userId, pageable)
                .stream()
                .map(postMapper::postListToPostDto)
                .collect(Collectors.toList());
    }

    public PostDto getPostById(UUID id) {
        return postsRepository.findPostById(id)
                .map(postMapper::postToPostDetailsDto)
                .orElseThrow(NotFoundException::new);
    }

    public Post create(PostCreationDto postDto) {
        Post post = postMapper.postCreationDtoToPost(postDto);
        return postsRepository.save(post);
    }

    public PostDto update(PostUpdatingDto postDto) {
        checkAccess(postDto.getId());
        postsRepository.update(postDto.getId(), postDto.getBody(), postDto.getImageId());
        return getPostById(postDto.getId());
    }

    public void softDelete(UUID id) {
        checkAccess(id);
        postsRepository.softDeletePostById(id);
    }

    private void checkAccess(UUID id) {
        var authorId = postsRepository.findById(id)
                .map(c -> c.getUser().getId())
                .orElseThrow(NotFoundException::new);

        if (!authorId.equals(TokenService.getUserId())) {
            throw new ForbiddenException();
        }
    }
}
