import React, {useEffect} from "react";
import styles from "./styles.module.scss";
import {Button, Card, Icon} from "semantic-ui-react";
import {IRootState} from "../../store";
import {connect, ConnectedProps} from "react-redux";
import {NavLink, RouteComponentProps} from "react-router-dom";
import {applyVacancy, loadVacancy} from "../../store/actions/vacancies";
import {getUserId} from "../../helpers/tokenParser";
import {withVacancyId} from "../../helpers/utils";
import {links} from "../../helpers/links";

interface UrlParams {
    vacancyId: string;
}

const VacancyDetails: React.FC<VacancyDetailsProps & RouteComponentProps<UrlParams>> = ({vacancy, loadVacancy, applyVacancy, match}) => {
    useEffect(() => {
        loadVacancy(match.params.vacancyId)
    }, [match.params.vacancyId])

    const isCreatedByCurrentUser = vacancy?.recruiter.id === getUserId()

    const getApplyButton = () => {
        if (!isCreatedByCurrentUser) {
            return vacancy?.applied
                ? <Button disabled={true} positive>Applied</Button>
                : <Button onClick={() => applyVacancy(match.params.vacancyId)} primary>Apply</Button>
        } else {
            return <></>
        }
    }

    return (
        <Card className={styles.vacancy}>
            <Card.Content header={vacancy?.title}/>
            <Card.Content className={styles.description}
                          description={vacancy?.description}/>
            <Card.Content extra>
                <Icon name='user'/> {vacancy?.candidatesCount}
            </Card.Content>
            {getApplyButton()}
            {isCreatedByCurrentUser &&
                <NavLink to={withVacancyId(links.candidatesForVacancy, vacancy?.id)}>
                    <Button primary className={styles.viewCandidates}>View candidates</Button>
                </NavLink>
            }
        </Card>
    )
}

const mapStateToProps = (state: IRootState) => ({
    vacancy: state.vacancies.expandedVacancy
})

const mapDispatchToProps = {
    loadVacancy,
    applyVacancy
}

const connector = connect(mapStateToProps, mapDispatchToProps);

type VacancyDetailsProps = ConnectedProps<typeof connector>

export default connector(VacancyDetails)