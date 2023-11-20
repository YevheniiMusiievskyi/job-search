package com.kpi.job_search.comment;

import com.kpi.job_search.comment.dto.CommentDetailsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommentMessagingService {

	private final SimpMessagingTemplate simpMessagingTemplate;

	public void sendComment(CommentDetailsDto comment) {
		simpMessagingTemplate.convertAndSend(getTopic(comment.getPostId()), comment);
	}

	public void sendCommentUpdate(CommentDetailsDto comment) {
		simpMessagingTemplate.convertAndSend(getTopic(comment.getPostId()) + "/update", comment);
	}

	public void sendCommentDelete(UUID postId, UUID commentId) {
		simpMessagingTemplate.convertAndSend(getTopic(postId) + "/delete", commentId);
	}

	private String getTopic(UUID postId) {
		return "/topic/posts/" + postId + "/comments";
	}
}
