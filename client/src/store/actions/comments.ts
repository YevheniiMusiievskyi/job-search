import {Dispatch} from "redux";
import {API} from "../../api";
import {thread} from "../slices/thread";
import {IComment, ICommentPostRequest, ICommentPutRequest} from "../../models/comments";

const { actions } = thread;

export const loadComments = (postId: string, page?: number, size?: number) => (dispatch: Dispatch) =>
    API.comments.fetchList(postId, page, size)
        .then((c: IComment[]) => dispatch(actions.setAllComments(c)));

export const loadMoreComments = filter => (dispatch: Dispatch) =>
    API.comments.fetchList(filter.postId, filter.page, filter.size)
        .then((c: IComment[]) => dispatch(actions.loadMoreComments(c)));

export const addComment = (postId: string, comment: ICommentPostRequest) =>
    API.comments.add(postId, comment);

export const applyAddComment = (comment: IComment) => (dispatch: Dispatch) =>
    dispatch(actions.addComment(comment));

export const updateComment = (postId: string, comment: ICommentPutRequest) =>
    API.comments.update(postId, comment);

export const applyUpdateComment = (comment: IComment) => (dispatch: Dispatch) =>
    dispatch(actions.updateComment(comment));

export const deleteComment = (postId: string, id: string) =>
    API.comments.delete(postId, id);

export const applyDeleteComment = (id: string) => (dispatch: Dispatch) =>
    dispatch(actions.deleteComment(id));

export const likeComment = (id: string) => (dispatch: Dispatch) =>
    commentReaction(id, true)(dispatch)

export const dislikeComment = (id: string) => (dispatch: Dispatch) =>
    commentReaction(id, false)(dispatch)

const commentReaction = (id: string, isLike: boolean) => (dispatch: Dispatch) =>
    API.comments.reaction(id, { isLike })
        .then(() => {
            API.comments.fetchReactions(id)
                .then(r => dispatch(actions.setCommentReactions(r)))
        })