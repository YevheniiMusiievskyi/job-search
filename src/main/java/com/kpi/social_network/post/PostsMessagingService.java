package com.kpi.social_network.post;

import com.kpi.social_network.auth.TokenService;
import com.kpi.social_network.post.dto.PostDto;
import com.kpi.social_network.postReactions.dto.PostReactionMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostsMessagingService {

	private final SimpMessagingTemplate simpMessagingTemplate;
	private final String TOPIC = "/topic/posts/";

	public void sendPost(PostDto post) {
		send(TOPIC, post);
	}

	public void sendPostUpdate(PostDto post) {
		send(TOPIC  + "update/", post);
	}

	public void sendPostDeleted(UUID postId) {
		send(TOPIC + "delete/", postId);
	}

	private void send(String topic, Object payload) {
		String userId = TokenService.getUserId().toString();
		simpMessagingTemplate.convertAndSend(topic, payload);
		simpMessagingTemplate.convertAndSend(topic + userId, payload);
	}

	public void sendLikeReaction(PostReactionMessage reaction) {
		/*simpMessagingTemplate.convertAndSendToUser(
				userId, "/queue/posts/like", reaction
		);*/
	}
}
