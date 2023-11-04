import {IUser} from "./auth";

export interface IComment {
    id: string;
    body: string;
    user: IUser;
    likeCount: number;
    dislikeCount: number;
    createdAt: string;
}

export interface ICommentPostRequest {
    body: string;
}

export interface ICommentPutRequest {
    id: string;
    body: string;
}

export interface ICommentReactionRequest {
    isLike: boolean;
}
