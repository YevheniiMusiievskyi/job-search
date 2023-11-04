import {IImage} from "./images";
import {IUser} from "./auth";

export interface IPost {
    id: string;
    image?: IImage;
    body: string;
    user: IUser;
    likeCount: number;
    dislikeCount: number;
    commentCount: number;
    comments?: any[];
    createdAt: string;
}

export interface IPostPostRequest {
    imageId: string | undefined;
    body: string;
}

export interface IPostPutRequest {
    id: string;
    body: string;
    image?: IImage;
}

export interface PostReactions {
    id: string;
    likeCount: number;
    dislikeCount: number;
}

export interface IPostReactionRequest {
    isLike: boolean;
}
