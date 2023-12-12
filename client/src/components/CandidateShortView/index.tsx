import {IUserProfile} from "../../models/userProfile";
import React from "react";
import {Card, Grid, Segment} from "semantic-ui-react";
import {trimToSize, withUserId} from "../../helpers/utils";
import styles from "./styles.module.scss"
import SkillsView from "../SkillsView";
import {links} from "../../helpers/links";

interface UserProfileViewProps {
    userProfile: IUserProfile
    userId: string
}

const maxDescriptionLength = 300;

const CandidateShortView: React.FC<UserProfileViewProps> = ({userProfile, userId}) => {
    return (
        <Card className={styles.candidate} href={withUserId(links.candidate, userId)}>
            <Card.Content header={userProfile.profile?.title}/>
            <Card.Content description={trimToSize(userProfile.profile?.description, maxDescriptionLength)}/>
            <Card.Content>
                <SkillsView skills={userProfile.profile?.skills} />
            </Card.Content>
        </Card>
    )
}

export default CandidateShortView;