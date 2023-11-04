import React, {FC, useState} from 'react';
import {connect, ConnectedProps} from 'react-redux';
import {Modal, Comment as CommentUI, Header, Loader} from 'semantic-ui-react';
import Post from 'src/components/Post';
import Comment from 'src/components/Comment';
import CommentInput from 'src/components/CommentInput';
import Spinner from 'src/components/Spinner';
import {IRootState} from "src/store";
import {deletePost, dislikePost, likePost, toggleExpandedPost, updatePost} from "../../store/actions/posts";
import {IPost} from "../../models/post";
import {
    addComment,
    applyAddComment, applyDeleteComment, applyUpdateComment,
    deleteComment,
    dislikeComment,
    likeComment, loadMoreComments,
    updateComment
} from "../../store/actions/comments";
import PostInput from "../../components/PostInput";
import * as images from "../../store/actions/images";
import CommentWebSocket from "../../components/WebSocket/CommentWebSocket";
import {IComment} from "../../models/comments";
import InfiniteScroll from 'react-infinite-scroller';

const filter = {
    page: 0,
    size: 10
}

interface HocProps {
    hideExpandedPost(): void;
}

const ExpandedPost: FC<ExpandedPostProps & HocProps> = (
    {
        post,
        hasMoreComments,
        likePost,
        dislikePost,
        toggleExpandedPost,
        loadMoreComments,
        applyAddComment,
        applyUpdateComment,
        applyDeleteComment,
        likeComment,
        dislikeComment,
        hideExpandedPost
    }) => {
    const [updatingCommentId, setUpdatingCommentId] = useState<string | null>(null);
    const [updatingPost, setUpdatingPost] = useState<IPost | null>(null);

    const loadComments = () => {
        loadMoreComments({ postId: post?.id, ...filter });
        filter.page++;
    }

    const handleClose = () => {
        filter.page = 0;
        hideExpandedPost();
    }

    return (
        <Modal dimmer="blurring" centered={false} open onClose={handleClose}>
            {post
                ? (
                    <Modal.Content>
                        {updatingPost !== post ? (
                            <Post post={post} likePost={likePost} dislikePost={dislikePost}
                                  toggleExpandedPost={toggleExpandedPost}
                                  setUpdatingPost={setUpdatingPost} deletePost={deletePost}/>
                        ) : (
                            <PostInput post={post} submit={updatePost} uploadImage={images.uploadImage}
                                       close={() => setUpdatingPost(null)}/>
                        )}
                        <CommentUI.Group style={{maxWidth: '100%'}}>
                            <Header as="h3" dividing>
                                Comments
                            </Header>
                            <InfiniteScroll pageStart={0} loadMore={loadComments} hasMore={hasMoreComments}
                                            loader={<Loader active inline="centered" key={0}/>}>
                                {post.comments && post.comments.map(
                                    (comment: IComment) => (updatingCommentId !== comment.id ? (
                                            <Comment key={comment.id} comment={comment} postId={post.id}
                                                     likeComment={likeComment}
                                                     dislikeComment={dislikeComment} deleteComment={deleteComment}
                                                     setUpdatingCommentId={setUpdatingCommentId}/>
                                        ) : (
                                            <CommentInput comment={comment} postId={post.id} submit={updateComment}
                                                          close={() => setUpdatingCommentId(null)}/>
                                        )
                                    ))}
                            </InfiniteScroll>
                            <CommentInput postId={post.id} submit={addComment}/>
                        </CommentUI.Group>
                    </Modal.Content>
                )
                : <Spinner/>}
            {post?.id &&
            <CommentWebSocket
                postId={post.id}
                applyAddComment={applyAddComment}
                applyUpdateComment={applyUpdateComment}
                applyDeleteComment={applyDeleteComment}
            />
            }
        </Modal>
    );
};

const mapStateToProps = (state: IRootState) => ({
    post: state.posts.expandedPost,
    hasMoreComments: state.posts.hasMoreComments
});

const mapDispatchToProps = {
    likePost,
    dislikePost,
    toggleExpandedPost,
    loadMoreComments,
    applyAddComment,
    applyUpdateComment,
    applyDeleteComment,
    likeComment,
    dislikeComment
}

const connector = connect(
    mapStateToProps,
    mapDispatchToProps
);

type ExpandedPostProps = ConnectedProps<typeof connector>;

export default connector(ExpandedPost);
