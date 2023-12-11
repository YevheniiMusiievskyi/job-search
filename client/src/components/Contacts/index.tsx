import React, {useState} from "react";
import {IContacts} from "../../models/userProfile";
import UserProfileInput from "../UserProfileInput";
import {Button, Grid, Image, Input} from "semantic-ui-react";
import {getUserImgLink} from "../../helpers/imageHelper";
import AvatarUploader from "../AvatarUploader";
import {getOrEmpty} from "../../helpers/utils";

interface IContactsProps {
    contacts: IContacts | null,
    avatar: string | null,
    uploadAvatar(file: File): void
    updateContacts(contacts: IContacts): void
}

const Contacts: React.FC<IContactsProps> = ({ contacts, avatar, uploadAvatar, updateContacts }) => {

    const [fullName, setFullName] = useState(getOrEmpty(contacts?.fullName))
    const [email, setEmail] = useState(getOrEmpty(contacts?.email))
    const [phone, setPhone] = useState(getOrEmpty(contacts?.phone))

    const handleSave = () => {
        updateContacts({
            fullName,
            email,
            phone
        })
    }

    return (
        <>
            <Grid.Row>
                <Grid.Column>
                    <Image centered src={getUserImgLink(avatar)} size="small" circular/>
                    <AvatarUploader uploadAvatar={uploadAvatar}/>
                </Grid.Column>
            </Grid.Row>
            <UserProfileInput rowName="Full name">
                <Input name="fullName" placeholder="Full name" value={fullName}
                    onChange={ev => setFullName(ev.target.value)}/>
            </UserProfileInput>
            <UserProfileInput rowName="Email" >
                <Input name="email" placeholder="email" type="email" value={email}
                    onChange={ev => setEmail(ev.target.value)} />
            </UserProfileInput>
            <UserProfileInput rowName="Phone">
                <Input name="phone" placeholder="phone" value={phone}
                       onChange={ev => setPhone(ev.target.value)}/>
            </UserProfileInput>
            <Button onClick={handleSave} primary>Save</Button>
        </>
    )
}

export default Contacts

