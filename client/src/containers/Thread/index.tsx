/* eslint-disable no-unused-vars */
import React, {useEffect, useState} from 'react';
import {connect, ConnectedProps} from 'react-redux';
import ExpandedPost from 'src/containers/ExpandedPost';
import Post from 'src/components/Post';
import PostInput from "../../components/PostInput";
import {Loader} from 'semantic-ui-react';
import InfiniteScroll from 'react-infinite-scroller';

import styles from './styles.module.scss';
import {IRootState} from "src/store";
import {
    addPost, applyAddPost, applyDeletePost, applyUpdatePost,
    deletePost,
    dislikePost,
    likePost,
    loadMorePosts,
    toggleExpandedPost,
    updatePost, loadPosts
} from "../../store/actions/posts";
import {IPost} from "../../models/post";
import * as images from "../../store/actions/images";
import PostWebSocket from "../../components/WebSocket/PostWebSocket";
import { NotificationContainer } from 'react-notifications';
import { RouteComponentProps, useHistory } from 'react-router-dom';

interface UrlParams {
    postId: string | undefined;
    userId: string | undefined;
}

const postsFilter = {
    page: 0,
    size: 25
}

const Thread: React.FC<ThreadProps & RouteComponentProps<UrlParams>> = (
    {
        user,
        posts = [],
        expandedPost,
        hasMorePosts,
        loadMorePosts,
        applyAddPost,
        applyUpdatePost,
        applyDeletePost,
        likePost,
        dislikePost,
        toggleExpandedPost,
        loadPosts,
        match
    }) => {

    const history = useHistory();
    const [updatingPost, setUpdatingPost] = useState<IPost | null>(null);
    const { userId, postId } = { ...match.params };

    const toggleExpandedPostWrap = (postId: string | null) => {
        setUpdatingPost(null);
        toggleExpandedPost(postId);

        const newUrl = "/" + (userId ? `user/${userId}/` : "") + (postId ? `post/${postId}` : "");
        history.push(newUrl);
    };

    useEffect(() => {
        window.scrollTo(0, 0)
        postsFilter.page = 0;

        if (postId) {
            toggleExpandedPost(postId);
        }
        loadPosts({ ...postsFilter, userId })
    }, [match.params.userId])

    const getMorePosts = () => {
        const filter = { ...postsFilter, userId };
        postsFilter.page === 0 ? loadPosts(filter) : loadMorePosts(filter);

        postsFilter.page++;
    };

    return (
        <div>
            {(userId && user?.id === userId || !userId) &&
                <div className={styles.addPostForm}>
                    <PostInput submit={addPost} uploadImage={images.uploadImage}/>
                </div>}
            <InfiniteScroll className={styles.scroll} pageStart={-1} loadMore={getMorePosts} hasMore={hasMorePosts}
                            loader={<Loader active inline="centered" key={0} />}>
                {posts?.map((post: IPost) => (
                    updatingPost !== post ? (
                        <Post post={post} likePost={likePost} dislikePost={dislikePost}
                              toggleExpandedPost={toggleExpandedPostWrap}
                              setUpdatingPost={setUpdatingPost} deletePost={deletePost} key={post.id}/>
                    ) : (
                        <PostInput post={post} submit={updatePost} uploadImage={images.uploadImage}
                                    close={() => setUpdatingPost(null)}/>
                    )
                ))}
            </InfiniteScroll>
            <NotificationContainer />
            {user &&
                <PostWebSocket
                    userId={userId}
                    applyAddPost={applyAddPost}
                    applyUpdatePost={applyUpdatePost}
                    applyDeletePost={applyDeletePost}
                />}
            {expandedPost && <ExpandedPost hideExpandedPost={() => toggleExpandedPostWrap(null)} />}
        </div>
    );
};

const mapStateToProps = (state: IRootState) => ({
    user: state.profile.user,
    posts: state.posts.posts,
    hasMorePosts: state.posts.hasMorePosts,
    expandedPost: state.posts.expandedPost
});

const mapDispatchToProps = {
    loadMorePosts,
    applyAddPost,
    applyUpdatePost,
    applyDeletePost,
    likePost,
    dislikePost,
    toggleExpandedPost,
    loadPosts
};

const connector = connect(
    mapStateToProps,
    mapDispatchToProps
);

type ThreadProps = ConnectedProps<typeof connector>;

export default connector(Thread);
