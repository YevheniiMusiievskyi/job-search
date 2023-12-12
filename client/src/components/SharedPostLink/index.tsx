import React, {useState, useRef} from 'react';
import {Modal, Input, Icon} from 'semantic-ui-react';
import styles from './styles.module.scss';

interface ISharedPostLinkProps {
    postId: string;
    close(): void;
}

const SharedPostLink: React.FC<ISharedPostLinkProps> = ({postId, close}) => {
    const [copied, setCopied] = useState(false);
    let input = useRef<any>();

    const copyToClipboard = e => {
        (input as any).select();
        document.execCommand('copy');
        e.target.focus();
        setCopied(true);
    };

    return (
        <Modal open onClose={close}>
          <Modal.Header className={styles.header}>
            <span>Share Post</span>
              {copied && (
                  <span>
                <Icon color="green" name="copy" />
                Copied
              </span>
              )}
          </Modal.Header>
          <Modal.Content>
            <Input fluid action={{
                color: 'teal',
                labelPosition: 'right',
                icon: 'copy',
                content: 'Copy',
                onClick: copyToClipboard
            }} value={`${window.location.origin}/share/${postId}`} ref={ref => {
                input = (ref as any);
            }} />
          </Modal.Content>
        </Modal>
    );
};

export default SharedPostLink;
