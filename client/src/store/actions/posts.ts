import {Dispatch} from "redux";
import {API} from "../../api";
import {IPost, IPostPostRequest, IPostPutRequest} from "../../models/post";
import {thread} from "../slices/thread";
import {act} from "react-dom/test-utils";

const { actions } = thread;

export const loadPosts = filter => (dispatch: Dispatch) =>
    API.posts.fetchList(filter.page, filter.size, filter.userId)
        .then((p: IPost[]) => {
            dispatch(actions.setAllPosts(p))
            dispatch(actions.setHasMorePosts())
        });

export const loadMorePosts = filter => (dispatch: Dispatch) =>
    API.posts.fetchList(filter.page, filter.size, filter.userId)
        .then((p: IPost[]) => dispatch(actions.loadMorePosts(p)));

export const addPost = (post: IPostPostRequest) =>
    API.posts.add(post);

export const applyAddPost = (post: IPost) => (dispatch: Dispatch) =>
    dispatch(actions.addPost(post));

export const updatePost = (post: IPostPutRequest) =>
    API.posts.update(post);

export const applyUpdatePost = (post: IPost) => (dispatch: Dispatch) =>
    dispatch(actions.updatePost(post));

export const deletePost = (postId: string) =>
    API.posts.delete(postId);

export const applyDeletePost = (postId: string) => (dispatch: Dispatch) =>
    dispatch(actions.deletePost(postId));

export const toggleExpandedPost = (postId: string | null) => (dispatch: Dispatch) => {
    if (postId) {
        API.posts.fetch(postId)
            .then(p => dispatch(actions.setExpandedPost(p)));
    } else {
        dispatch(actions.setExpandedPost(null));
    }
};

export const likePost = (postId: string) => (dispatch: Dispatch) =>
    postReaction(postId, true)(dispatch);

export const dislikePost = (postId: string) => (dispatch: Dispatch) =>
    postReaction(postId, false)(dispatch);

const postReaction = (postId: string, isLike: boolean) => (dispatch: Dispatch) =>
    API.posts.reaction(postId, { isLike })
        .then(() => {
            API.posts.fetchReactions(postId)
                .then(r => dispatch(actions.setReactions(r)))
        })

export const clearPosts = () => (dispatch: Dispatch) => {
    dispatch(actions.setAllPosts([]))
    dispatch(actions.setHasMorePosts())
}