package com.kpi.social_network.post;


import com.kpi.social_network.auth.TokenService;
import com.kpi.social_network.post.dto.PostCreationDto;
import com.kpi.social_network.post.dto.PostDto;
import com.kpi.social_network.post.dto.PostUpdatingDto;
import com.kpi.social_network.users.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.kpi.social_network.auth.TokenService.getUserId;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostsController {

    private final PostsService postsService;
    private final PostsMessagingService messagingService;

    @GetMapping
    public List<PostDto> get(@RequestParam(defaultValue="0") Integer page,
                             @RequestParam(defaultValue="10") Integer size,
                             @RequestParam(required = false) UUID userId) {
        return postsService.getAllPosts(page, size, userId);
    }

    @GetMapping("/{id}")
    public PostDto get(@PathVariable UUID id) {
        return postsService.getPostById(id);
    }

    @PostMapping
    public void post(@RequestBody PostCreationDto postDto) {
        postDto.setUserId(getUserId());
        var savedPost = postsService.create(postDto);
        messagingService.sendPost(postsService.getPostById(savedPost.getId()));
    }

    @PutMapping
    public void update(@RequestBody PostUpdatingDto postDto) {
        var updatedPost = postsService.update(postDto);
        messagingService.sendPostUpdate(updatedPost);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable UUID id) {
        postsService.softDelete(id);
        messagingService.sendPostDeleted(id);
    }
}
