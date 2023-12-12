import React from "react";
import {List} from "semantic-ui-react";
import {SemanticICONS} from "semantic-ui-react/dist/commonjs/generic";

interface ContactItemProps {
    icon: SemanticICONS
    content: string | null
}

const ContactItem: React.FC<ContactItemProps> = ({ icon, content }) => {

    if (!icon || !content) {
        return <></>
    }

    return (
        <List.Item>
            <List.Icon name={icon} />
            <List.Content>{content}</List.Content>
        </List.Item>
    )

}

export default ContactItem