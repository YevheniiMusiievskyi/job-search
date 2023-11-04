import React from 'react';
import {Comment as CommentUI, Dropdown, Icon, Label} from 'semantic-ui-react';
import moment from 'moment';
import {getUserImgLink} from 'src/helpers/imageHelper';
import store from '../../store';
import {IComment} from "src/models/comments";

interface ICommentProps {
    comment: IComment;
    postId: string;

    likeComment(id: string): void;

    dislikeComment(id: string): void;

    deleteComment(postId: string, id: string): void;

    setUpdatingCommentId(id: string): void;
}

const Comment: React.FC<ICommentProps> =
    ({
         comment,
         postId,
         likeComment,
         dislikeComment,
         deleteComment,
         setUpdatingCommentId
     }) => {
        const {id, body, createdAt, user, likeCount, dislikeCount} = comment;

        return (
            <CommentUI>
                <CommentUI.Avatar src={getUserImgLink(user.avatar)}/>
                <CommentUI.Content>
                    <CommentUI.Author as="a">
                        {user.username}
                    </CommentUI.Author>
                    <CommentUI.Metadata>
                        {moment(createdAt)
                            .fromNow()}
                    </CommentUI.Metadata>
                    {store.getState().profile.user?.id !== user.id ? '' : (
                        <Dropdown>
                            <Dropdown.Menu size="sm" title="">
                                <Dropdown.Item onClick={() => setUpdatingCommentId(id)}>Edit</Dropdown.Item>
                                <Dropdown.Item onClick={() => deleteComment(postId, id)}>Delete</Dropdown.Item>
                            </Dropdown.Menu>
                        </Dropdown>
                    )}
                    <CommentUI.Text>
                        {body}
                    </CommentUI.Text>
                    <Label basic size="small" as="a" onClick={() => likeComment(id)}>
                        <Icon name="thumbs up"/>
                        {likeCount}
                    </Label>
                    <Label basic size="small" as="a" onClick={() => dislikeComment(id)}>
                        <Icon name="thumbs down"/>
                        {dislikeCount}
                    </Label>
                </CommentUI.Content>
            </CommentUI>
        );
    };

export default Comment;
