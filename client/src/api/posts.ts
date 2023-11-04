import {client} from "./index";
import {IPost, IPostPostRequest, IPostPutRequest, IPostReactionRequest, PostReactions} from "../models/post";
import {AxiosResponse} from "axios";

export const posts = {
    fetchList(page?: number, size?: number, userId?: string): Promise<IPost[]> {
        return client.get("/posts", { params: { page, size, userId } })
            .then((r: AxiosResponse<IPost[]>) => r.data);
    },
    fetch(id: string): Promise<IPost> {
        return client.get(`/posts/${id}`)
            .then((r: AxiosResponse<IPost>) => r.data);
    },
    add(post: IPostPostRequest): Promise<IPost> {
        return client.post("/posts", post)
            .then((r: AxiosResponse<IPost>) => r.data);
    },
    update(post: IPostPutRequest): Promise<IPost> {
        return client.put("/posts", post)
            .then((r: AxiosResponse<IPost>) => r.data);
    },
    delete(id: string): Promise<void> {
        return client.delete(`/posts/${id}`);
    },
    fetchReactions(id: string): Promise<any> {
        return client.get(`/posts/${id}/reaction`)
            .then((r: AxiosResponse<PostReactions>) => r.data);
    },
    reaction(id: string, reaction: IPostReactionRequest): Promise<void> {
        return client.post(`/posts/${id}/reaction`, reaction);
    }
};
