import {FC, useEffect} from "react";
import {IPost} from "../../models/post";
import connectWebSocket from "../../helpers/websocket";

interface PostWebSocketProps {
    userId?: string;
    applyAddPost(post: IPost): void;
    applyUpdatePost(post: IPost): void;
    applyDeletePost(postId: string): void;
}

const PostWebSocket: FC<PostWebSocketProps> = ({ userId, applyAddPost, applyUpdatePost, applyDeletePost }) => {
    const userIdPath = userId ? userId : '';
    const topic = '/topic/posts';
    useEffect(() => {
        const stompClient = connectWebSocket([
            {
                destination: `${topic}/${userIdPath}`,
                callback: applyAddPost
            },
            {
                destination: `${topic}/update/${userIdPath}`,
                callback: applyUpdatePost
            },
            {
                destination: `${topic}/delete/${userIdPath}`,
                callback: applyDeletePost
            }
        ]);

        return () => {
            stompClient.disconnect();
        };
    }, [userId, applyAddPost, applyUpdatePost, applyDeletePost]);

    return <></>;
};

export default PostWebSocket;