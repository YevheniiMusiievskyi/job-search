import React, {useState} from 'react';
import {Form, Button, Icon, Image, Segment} from 'semantic-ui-react';

import styles from './styles.module.scss';
import {IPost, IPostPostRequest, IPostPutRequest} from "src/models/post";
import {IImage} from "src/models/images";

interface IPostInputProps {
    submit(post: IPostPostRequest | IPostPutRequest): void;

    uploadImage(image: File): Promise<IImage>;

    post?: IPost;

    close?(): void;
}

const PostInput: React.FC<IPostInputProps> = (
    {
        submit,
        uploadImage,
        post,
        close
    }) => {
    const [body, setBody] = useState(post?.body ? post.body : "");
    const [image, setImage] = useState<IImage | null>(post?.image ? post.image : null);
    const [isUploading, setIsUploading] = useState<boolean>(false);

    const handleAddPost = async () => {
        if (!body && !image) {
            return;
        }
        submit({
            id: post?.id,
            imageId: image?.id,
            body
        });
        setBody('');
        setImage(null);
        if (close) {
            close();
        }
    };

    const handleUploadFile = async ({target}) => {
        setIsUploading(true);
        try {
            const uploadedImage = await uploadImage(target.files[0]);
            setImage(uploadedImage);
        } finally {
            setIsUploading(false);
        }
    };

    return (
        <Segment>
            <Form onSubmit={handleAddPost}>
                <Form.TextArea name="body" value={body} placeholder="What is the news?"
                               onChange={ev => setBody((ev.target as HTMLTextAreaElement).value)}/>
                {image?.link && (
                    <div className={styles.imageWrapper}>
                        <Image className={styles.image} src={image?.link} alt="post"/>
                    </div>
                )}
                <Button color="teal" icon labelPosition="left" as="label" loading={isUploading} disabled={isUploading}>
                    <Icon name="image"/>
                    Attach image
                    <input name="image" type="file" onChange={handleUploadFile} hidden/>
                </Button>
                <Button floated="right" color="blue" type="submit">Post</Button>
            </Form>
        </Segment>
    );
};

export default PostInput;
