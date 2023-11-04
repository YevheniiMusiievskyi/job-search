import Avatar from "react-avatar-edit";
import {FC, useState} from "react";
import * as React from "react";
import {Button, Modal} from "semantic-ui-react";
import styles from './styles.module.scss';

interface AvatarUploaderProps {
    uploadAvatar(avatar: File): void;
}

const AvatarUploader: FC<AvatarUploaderProps> = ({ uploadAvatar }) => {
    const [open, setOpen] = useState(false)
    const [preview, setPreview] = useState<any>();

    const onCrop = preview => {
        setPreview(preview);
    }

    const close = () => {
        setOpen(false);
    }

    const urlToFile = (url: string) => {
        const mimeType = (url.match(/^data:([^;]+);/)||'')[1];
        const filename = "avatar.png";
        return (fetch(url)
                .then(function(res){return res.arrayBuffer();})
                .then(function(buf){return new File([buf], filename, {type:mimeType});})
        );
    }

    const submitUploadAvatar = async () => {
        uploadAvatar(await urlToFile(preview));
        close();
    }

    return (
        <Modal
            className={styles.fileUploader}
            open={open}
            onClose={close}
            trigger={<Button className={styles.openModalButton} onClick={() => setOpen(true)}>Change Avatar</Button>}
        >
            <Avatar
                width={400}
                height={300}
                onCrop={onCrop}
                onClose={() => setPreview(null)}
            />
            <Button className={styles.submitButton} onClick={submitUploadAvatar}>Upload</Button>
        </Modal>
    )
}

export default AvatarUploader;