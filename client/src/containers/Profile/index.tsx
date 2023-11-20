import React, {useEffect, useState} from 'react';
import {connect, ConnectedProps} from 'react-redux';
import {getUserImgLink} from 'src/helpers/imageHelper';
import {
    Grid,
    Image,
    Input,
    Form
} from 'semantic-ui-react';
import {IRootState} from "src/store";
import AvatarUploader from "../../components/AvatarUploader";
import {uploadAvatar} from "../../store/actions/images";
import {loadUserProfile} from "../../store/actions/userProfile";
import {RouteComponentProps} from "react-router-dom";
import SkillsInput from "../../components/SkillsInput";
import {loadAllExistingSkills} from "../../store/actions/skills";

interface UrlParams {
    userId: string | undefined;
}

const Profile: React.FC<ProfileProps & RouteComponentProps<UrlParams>> = ({user, userProfile, existingSkills, uploadAvatar, loadUserProfile, loadAllExistingSkills, match}) => {
    const isCurrentUser = user?.id === match.params.userId;

    useEffect(() => {
        if (user != null) {
            loadUserProfile(user.id)
        }
    }, [match.params.userId])

    useEffect(() => {
        if (isCurrentUser) {
            loadAllExistingSkills()
        }
    })


    const [title, setTitle] = useState(userProfile?.title ? userProfile.title : '')
    const [description, setDescription] = useState(userProfile?.description ? userProfile.description : '')
    const [skills, setSkills] = useState(userProfile?.skills ? userProfile.skills : [])

    const handleSkillAddition = skill => {
        setSkills([...skills, skill]);
    };

    const handleDelete = i => {
        setSkills(skills.filter((tag, index) => index !== i));
    };

    const handleSkillDrag = (skill, currPos, newPos) => {
        const newTags = skills.slice();

        newTags.splice(currPos, 1);
        newTags.splice(newPos, 0, skill);

        // re-render
        setSkills(newTags);
    };

    return (
        <Grid container textAlign="center" style={{paddingTop: 30}}>
            <Grid.Column>
                <Image centered src={getUserImgLink(user?.avatar)} size="medium" circular/>
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
            <Grid.Column>
                <Form.Input name="title" value={title} placeholder='Title' disabled={isCurrentUser}
                               onChange={ev => setTitle((ev.target as HTMLInputElement).value)} />
            </Grid.Column>
            <Grid.Column>
                <Form.TextArea name="description" value={description} placeholder='Description' disabled={isCurrentUser}
                               onChange={ev => setDescription((ev.target as HTMLTextAreaElement).value)}/>
            </Grid.Column>
            <Grid.Column>
                <SkillsInput
                    tags={skills}
                    suggestions={existingSkills ? existingSkills : []}
                    readonly={isCurrentUser}
                    handleSkillAddition={handleSkillAddition}
                    handleSkillDelete={handleDelete}
                    handleSkillDrag={handleSkillDrag}
                />
            </Grid.Column>
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
    loadAllExistingSkills
}

const connector = connect(mapStateToProps, mapDispatchToProps);

type ProfileProps = ConnectedProps<typeof connector>;

export default connector(Profile);
