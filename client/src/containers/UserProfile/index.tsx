import React, {useEffect, useState} from 'react';
import {connect, ConnectedProps} from 'react-redux';
import {getUserImgLink} from 'src/helpers/imageHelper';
import {
    Grid,
    Image,
    Input,
    Form, Button
} from 'semantic-ui-react';
import {IRootState} from "src/store";
import AvatarUploader from "../../components/AvatarUploader";
import {uploadAvatar} from "../../store/actions/images";
import {createUserProfile, loadUserProfile, updateUserProfile} from "../../store/actions/userProfile";
import {RouteComponentProps} from "react-router-dom";
import SkillsInput from "../../components/SkillsInput";
import {loadAllExistingSkills} from "../../store/actions/skills";
import {ITag} from "../../models/skills";
import {IUserProfilePostRequest} from "../../models/userProfile";
import {mapSkillToTag, mapTagToSkillPostRequest} from "../../helpers/mappers/skillsMapper";
import styles from './styles.module.scss';

interface UrlParams {
    userId: string;
}

const UserProfile: React.FC<UserProfileProps & RouteComponentProps<UrlParams>> = (
    {
        user,
        userProfile,
        existingSkills,
        uploadAvatar,
        loadUserProfile,
        loadAllExistingSkills,
        createUserProfile,
        updateUserProfile,
        match
    }) => {
    const userId = match.params.userId;
    const isCurrentUser = user?.id === match.params.userId;

    useEffect(() => {
        loadUserProfile(userId)
    }, [match.params.userId])

    useEffect(() => {
        setTitle(userProfile?.title ? userProfile.title : '');
        setDescription(userProfile?.description ? userProfile.description : '')
        setTags(userProfile?.skills ? userProfile.skills.map(mapSkillToTag) : [])
    }, [userProfile])

    useEffect(() => {
        if (isCurrentUser) {
            loadAllExistingSkills()
        }
    }, [match.params.userId])

    const [title, setTitle] = useState('')
    const [description, setDescription] = useState('')
    const [tags, setTags] = useState([] as ITag[])

    const handleSkillAddition = (tag: ITag) => {
        setTags([...tags, tag]);
    };

    const handleDelete = i => {
        setTags(tags.filter((tag, index) => index !== i));
    };

    const handleSkillDrag = (tag, currPos, newPos) => {
        const newTags = tags.slice();

        newTags.splice(currPos, 1);
        newTags.splice(newPos, 0, tag);

        // re-render
        setTags(newTags);
    };

    const handleSave = () => {
        const mappedSkills = tags.map(mapTagToSkillPostRequest)

        const userProfileRequest: IUserProfilePostRequest = {
            id: userProfile ? userProfile.id : null,
            title,
            description,
            skills: mappedSkills
        }

        if (userProfileRequest.id) {
            updateUserProfile(userProfileRequest)
        } else {
            createUserProfile(userProfileRequest)
        }
    }

    return (
        <Grid container textAlign="center" style={{paddingTop: 30}}>
            <Grid.Row>
                <Grid.Column>
                    <Image centered src={getUserImgLink(user?.avatar)} size="small" circular/>
                    <AvatarUploader uploadAvatar={uploadAvatar}/>
                    <br/>
                    <Input
                        icon="user"
                        iconPosition="left"
                        placeholder="Username"
                        type="text"
                        disabled
                        value={user?.username}
                    />
                    <br/>
                    <br/>
                    <Input
                        icon="at"
                        iconPosition="left"
                        placeholder="Email"
                        type="email"
                        disabled
                        value={user?.email}
                    />
                </Grid.Column>
            </Grid.Row>
            <Grid.Row>
                <Grid.Column className={styles.gridColumn1} textAlign="left">
                    <div>
                        Title
                    </div>
                </Grid.Column>
                <Grid.Column className={styles.gridColumn2}>
                    <Input className={styles.gridColumn2Item}
                           name="title" value={title} placeholder='Title' disabled={!isCurrentUser}
                           onChange={ev => setTitle((ev.target as HTMLInputElement).value)}/>
                </Grid.Column>
            </Grid.Row>
            <Grid.Row className={styles.gridRow}>
                <Grid.Column className={styles.gridColumn1} textAlign="left">
                    Work experience
                </Grid.Column>
                <Grid.Column className={styles.gridColumn2}>
                    <Form className={styles.gridColumn2Item}>
                        <Form.TextArea
                            name="description" placeholder='Description' rows={5}
                            value={description} disabled={!isCurrentUser}
                            onChange={ev => setDescription((ev.target as HTMLTextAreaElement).value)}/>

                    </Form>
                </Grid.Column>
            </Grid.Row>
            <Grid.Row>
                <Grid.Column className={styles.gridColumn1} textAlign="left">
                    Skills
                </Grid.Column>
                <Grid.Column className={styles.gridColumn2}>
                    <SkillsInput
                        tags={tags}
                        suggestions={existingSkills ? existingSkills.map(mapSkillToTag) : []}
                        readonly={isCurrentUser}
                        handleSkillAddition={handleSkillAddition}
                        handleSkillDelete={handleDelete}
                        handleSkillDrag={handleSkillDrag}
                    />
                </Grid.Column>
            </Grid.Row>
            <Button onClick={handleSave} primary>Save</Button>
        </Grid>
    )
};

const mapStateToProps = (state: IRootState) => ({
    user: state.profile.user,
    userProfile: state.userProfile.userProfile,
    existingSkills: state.skills.existingSkills
});

const mapDispatchToProps = {
    uploadAvatar,
    loadUserProfile,
    loadAllExistingSkills,
    createUserProfile,
    updateUserProfile
}

const connector = connect(mapStateToProps, mapDispatchToProps);

type UserProfileProps = ConnectedProps<typeof connector>;

export default connector(UserProfile);
