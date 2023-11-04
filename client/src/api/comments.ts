import {client} from "./index";
import {IComment, ICommentPostRequest, ICommentPutRequest, ICommentReactionRequest} from "../models/comments";
import {AxiosResponse} from "axios";

export const comments = {
    fetchList(postId: string, from?: number, count?: number): Promise<IComment[]> {
        return client.get(`/${postId}/comments`, { params: { page: from, size: count } })
            .then((r: AxiosResponse<IComment[]>) => r.data);
    },
    add(postId: string, comment: ICommentPostRequest): Promise<IComment> {
        return client.post(`/${postId}/comments`, comment)
            .then((r: AxiosResponse<IComment>) => r.data);
    },
    update(postId: string, comment: ICommentPutRequest): Promise<IComment> {
        return client.put(`/${postId}/comments`, comment)
            .then((r: AxiosResponse<IComment>) => r.data);
    },
    delete(postId: string, id: string): Promise<void> {
        return client.delete(`/${postId}/comments/${id}`);
    },
    fetchReactions(id: string): Promise<any> {
        return client.get(`/comments/${id}/reaction`)
            .then(r => r.data);
    },
    reaction(id: string, reaction: ICommentReactionRequest): Promise<void> {
        return client.post(`/comments/${id}/reaction`, reaction);
    }
};
