import React, {useState} from 'react';
import {Form, Button} from 'semantic-ui-react';
import {IComment, ICommentPostRequest, ICommentPutRequest} from "src/models/comments";
import styles from './styles.module.scss';

interface CommentInputProps {
    comment?: IComment;
    postId: string;
    submit(postId: string, comment: ICommentPostRequest | ICommentPutRequest): void;
    close?(): void;
}

const CommentInput: React.FC<CommentInputProps> = ({
    comment,
    postId,
    submit,
    close
}) => {
    const [body, setBody] = useState<string>(comment ? comment.body : "");

    const handleSubmit = () => {
        if (!body) {
            return;
        }
        submit(postId, { id: comment?.id, body });

        setBody('');
        if (close) {
            close();
        }
    };

    return (
        <Form className={styles.form} onSubmit={handleSubmit}>
            <Form.TextArea
                value={body}
                placeholder="Type a comment..."
                rows={5}
                onChange={ev => setBody((ev.target as HTMLTextAreaElement).value)}
            />
            <Button type="submit" content="Post comment" labelPosition="left" icon="edit" primary />
        </Form>
    );
};

export default CommentInput;
