import React, {useEffect, useState} from 'react';
import {connect, ConnectedProps} from 'react-redux';
import {
    Grid, Menu
} from 'semantic-ui-react';
import {IRootState} from "src/store";
import {uploadAvatar} from "../../store/actions/images";
import {loadUserProfile, updateContacts, updateProfile} from "../../store/actions/userProfile";
import {RouteComponentProps} from "react-router-dom";
import {loadAllExistingSkills} from "../../store/actions/skills";
import Profile from "../../components/Profile";
import Contacts from "../../components/Contacts";

interface UrlParams {
    userId: string;
}

enum MenuItem {
    PROFILE = "Profile", CONTACTS = "Contacts"
}

const UserProfile: React.FC<UserProfileProps & RouteComponentProps<UrlParams>> = (
    {
        user,
        userProfile,
        existingSkills,
        uploadAvatar,
        loadUserProfile,
        loadAllExistingSkills,
        updateProfile,
        updateContacts,
        match
    }) => {
    const userId = match.params.userId;
    const isCurrentUser = user?.id === match.params.userId;

    useEffect(() => {
        loadUserProfile(userId)
    }, [match.params.userId])

    useEffect(() => {
        if (isCurrentUser) {
            loadAllExistingSkills()
        }
    }, [match.params.userId])

    const [activeMenu, setActiveMenu] = useState(MenuItem.PROFILE)

    return (
        <Grid container textAlign="center">
            <Grid.Row>
                <Menu pointing secondary>
                    <Menu.Item
                        name={MenuItem.PROFILE}
                        active={activeMenu === MenuItem.PROFILE}
                        onClick={() => setActiveMenu(MenuItem.PROFILE)}
                    />
                    <Menu.Item
                        name={MenuItem.CONTACTS}
                        active={activeMenu === MenuItem.CONTACTS}
                        onClick={() => setActiveMenu(MenuItem.CONTACTS)}
                    />
                </Menu>
            </Grid.Row>
            {activeMenu === MenuItem.PROFILE
                ? (
                <Profile
                    profile={userProfile?.profile}
                    existingSkills={existingSkills}
                    updateProfile={updateProfile}
                />
            ) : (
                <Contacts
                    contacts={userProfile?.contacts}
                    avatar={user?.avatar ? user.avatar : null}
                    uploadAvatar={uploadAvatar}
                    updateContacts={updateContacts}
                />
            )
            }
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
    updateProfile,
    updateContacts
}

const connector = connect(mapStateToProps, mapDispatchToProps);

type UserProfileProps = ConnectedProps<typeof connector>;

export default connector(UserProfile);
