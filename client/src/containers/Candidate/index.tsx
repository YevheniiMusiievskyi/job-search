import {IRootState} from "../../store";
import React, {createRef, useEffect} from "react";
import {RouteComponentProps} from "react-router-dom";
import {Card, Header as HeaderUI, Image, List, Rail, Sticky} from "semantic-ui-react";
import {loadCandidate} from "../../store/actions/candidates";
import {connect, ConnectedProps} from "react-redux";
import SkillsView from "../../components/SkillsView";
import styles from "./styles.module.scss"
import ContactItem from "../../components/ContactItem";
import {getUserImgLink} from "../../helpers/imageHelper";

interface UrlParams {
    userId: string
}

const Candidate: React.FC<CandidateProps & RouteComponentProps<UrlParams>> = ({candidate, loadCandidate, match}) => {
    useEffect(() => {
        loadCandidate(match.params.userId)
    }, [match.params.userId])

    return (
        <div>
            <Card className={styles.candidate}>
                {candidate?.contacts &&
                    <Card.Content>
                        <Card.Header>
                            <HeaderUI>
                                <Image circular src={getUserImgLink(candidate?.contacts?.avatar)}/>
                                {' '}
                                {candidate?.contacts?.fullName}
                            </HeaderUI>
                        </Card.Header>
                    </Card.Content>}
                <Card.Content header={candidate?.profile?.title}/>
                <Card.Content className={styles.description}  description={candidate?.profile?.description}/>
                <Card.Content>
                    <SkillsView skills={candidate?.profile?.skills}/>
                </Card.Content>

                {candidate?.contacts &&
                    <Rail position="right">
                        <Card>
                            <Card.Content header="Contacts"/>
                            <Card.Content>
                                <List>
                                    <ContactItem icon="mail" content={candidate.contacts.email}/>
                                    <ContactItem icon="call" content={candidate.contacts.phone}/>
                                </List>
                            </Card.Content>
                        </Card>
                    </Rail>
                }

            </Card>
        </div>

    )
}

const mapStateToProps = (state: IRootState) => ({
    candidate: state.candidates.expandedCandidate
})

const mapDispatchToProps = {
    loadCandidate
}

const connector = connect(mapStateToProps, mapDispatchToProps)

type CandidateProps = ConnectedProps<typeof connector>

export default connector(Candidate)