import {createSlice} from "@reduxjs/toolkit";
import {IPost} from "../../models/post";

interface InitialState {
    posts: IPost[];
    hasMorePosts: boolean;
    expandedPost: IPost | null;
    hasMoreComments: boolean;
}

const initialState: InitialState = {
    posts: [],
    hasMorePosts: true,
    expandedPost: null,
    hasMoreComments: false
};

export const thread = createSlice({
    name: "thread",
    initialState,
    reducers: {
        setAllPosts(state, { payload: posts }) {
            state.posts = posts;
            state.hasMorePosts = Boolean(posts?.length);
        },
        loadMorePosts(state, { payload: posts }) {
            state.posts = state.posts.concat(posts);
            state.hasMorePosts = Boolean(posts?.length);
        },
        addPost(state, { payload: post }) {
            state.posts.unshift(post)
        },
        updatePost(state, { payload: post }) {
            state.posts = state.posts.map(p => p.id === post.id ? post : p);

            if (state.expandedPost && state.expandedPost.id === post.id) {
                state.expandedPost = {
                    ...post,
                    comments: state.expandedPost.comments
                }
            }
        },
        setReactions(state, { payload: reactions }) {
            state.posts = state.posts.map(p => {
                if (p.id === reactions.postId) {
                    p.likeCount = reactions.likeCount;
                    p.dislikeCount = reactions.dislikeCount;
                }

                return p;
            })

            if (state.expandedPost && state.expandedPost.id === reactions.postId) {
                state.expandedPost.likeCount = reactions.likeCount;
                state.expandedPost.dislikeCount = reactions.dislikeCount;
            }
        },
        deletePost(state, { payload: postId }) {
            state.posts = state.posts.filter(p => p.id !== postId);

            if (state.expandedPost && state.expandedPost.id === postId) {
                state.expandedPost = null;
            }
        },
        setExpandedPost(state, { payload: post }) {
            state.expandedPost = post;
            state.hasMoreComments = post ? !!post.commentCount : false;
            if (state.expandedPost) {
                state.expandedPost.comments = [];
            }
        },
        setHasMorePosts(state) {
            state.hasMorePosts = true;
        },
        setAllComments(state, { payload: comments }) {
            if (state.expandedPost) {
                state.expandedPost.comments = comments;
            }
        },
        loadMoreComments(state, { payload: comments }) {
            if (state.expandedPost) {
                if (!state.expandedPost.comments) {
                    state.expandedPost.comments = [];
                }
                state.expandedPost.comments = state.expandedPost.comments.concat(comments);
                state.hasMoreComments = !!comments?.length;
            }
        },
        addComment(state, { payload: comment }) {
            const expandedPost = state.expandedPost;
            if (expandedPost && expandedPost.comments) {
                expandedPost.comments.push(comment);
                expandedPost.commentCount++;
                state.posts.map(p => {
                    if (p.id === expandedPost.id) {
                        p.commentCount = expandedPost.commentCount;
                    }
                })
            }
        },
        updateComment(state, { payload: comment }) {
            if (state.expandedPost && state.expandedPost.comments) {
                state.expandedPost.comments = state.expandedPost.comments.map(c => c.id === comment.id ? comment : c);
            }
        },
        deleteComment(state, { payload: commentId }) {
            if (state.expandedPost && state.expandedPost.comments) {
                state.expandedPost.comments = state.expandedPost.comments.filter(c => c.id !== commentId);
                state.expandedPost.commentCount--;
            }
        },
        setCommentReactions(state, { payload: reactions }) {
            if (state.expandedPost && state.expandedPost.comments) {
                state.expandedPost.comments = state.expandedPost.comments.map(c => {
                    if (c.id === reactions.commentId) {
                        c.likeCount = reactions.likeCount;
                        c.dislikeCount = reactions.dislikeCount;
                    }

                    return c;
                })
            }
        }
    }
});

export default thread.reducer;