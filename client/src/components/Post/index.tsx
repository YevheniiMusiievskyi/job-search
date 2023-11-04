import React from 'react';
import {Card, Dropdown, Feed, Icon, Image, Label} from 'semantic-ui-react';
import moment from 'moment';
import styles from './styles.module.scss';
import store from '../../store';
import {IPost} from "src/models/post";
import {Link} from 'react-router-dom';
import {getUserImgLink} from "../../helpers/imageHelper";

interface IPostProps {
    post: IPost;

    likePost(id: string): void;

    dislikePost(id: string): void;

    toggleExpandedPost(id: string): void;

    setUpdatingPost(post: IPost): void;

    deletePost(id: string): void;
}

const Post: React.FC<IPostProps> = (
    {
        post,
        likePost,
        dislikePost,
        toggleExpandedPost,
        setUpdatingPost,
        deletePost
    }) => {
    const {
        id,
        image,
        body,
        user,
        likeCount,
        dislikeCount,
        commentCount,
        createdAt
    } = post;
    const date = moment(createdAt).fromNow();

    return (
        <Card className={styles.post} style={{width: '100%'}}>
            <Card.Content>
                <Feed>
                    <Feed.Event>
                        <Feed.Label className={styles.label} image={getUserImgLink(user.avatar)} />
                        <Feed.Content>
                            <Feed.Summary>
                                <Link className={styles.user} to={`/user/${user.id}`}>
                                    {user?.username}
                                </Link>
                            </Feed.Summary>
                            <Feed.Date className={styles.date}>
                                {date}
                            </Feed.Date>
                        </Feed.Content>
                    </Feed.Event>
                </Feed>
                <Card.Description className={styles.body}>
                    {body}
                </Card.Description>
            </Card.Content>
            {image && <Image src={image.link} wrapped ui={false}/>}
            <Card.Content>
                <Label basic size="small" as="a" className={styles.toolbarBtn} onClick={() => likePost(id)}>
                    <Icon name="thumbs up"/>
                    {likeCount}
                </Label>
                <Label basic size="small" as="a" className={styles.toolbarBtn} onClick={() => dislikePost(id)}>
                    <Icon name="thumbs down"/>
                    {dislikeCount}
                </Label>
                <Label basic size="small" as="a" className={styles.toolbarBtn}
                       onClick={() => toggleExpandedPost(id)}>
                    <Icon name="comment"/>
                    {commentCount}
                </Label>
                {store.getState().profile.user?.id !== user.id ? '' : (
                    <Dropdown>
                        <Dropdown.Menu size="sm" title="">
                            <Dropdown.Item onClick={() => setUpdatingPost(post)}>Edit</Dropdown.Item>
                            <Dropdown.Item onClick={() => deletePost(id)}>Delete</Dropdown.Item>
                        </Dropdown.Menu>
                    </Dropdown>
                )}
            </Card.Content>
        </Card>
    );
};

export default Post;
