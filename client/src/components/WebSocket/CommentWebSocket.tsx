import {FC, useEffect} from "react";
import connectWebSocket from "../../helpers/websocket";
import {IComment} from "../../models/comments";

interface CommentWebSocketProps {
    postId: string;
    applyAddComment(comment: IComment): void;
    applyUpdateComment(comment: IComment): void;
    applyDeleteComment(id: string): void;
}

const CommentWebSocket: FC<CommentWebSocketProps> = ({ postId, applyAddComment, applyUpdateComment, applyDeleteComment }) => {
    useEffect(() => {
        const stompClient = connectWebSocket([
            {
                destination: `/topic/posts/${postId}/comments`,
                callback: applyAddComment
            },
            {
                destination: `/topic/posts/${postId}/comments/update`,
                callback: applyUpdateComment
            },
            {
                destination: `/topic/posts/${postId}/comments/delete`,
                callback: applyDeleteComment
            }
        ]);

        return () => {
            stompClient.disconnect();
        };
    }, [postId, applyAddComment, applyUpdateComment, applyDeleteComment]);

    return <></>
}

export default CommentWebSocket