import React, {useEffect} from "react";
import styles from "./styles.module.scss";
import {Button, Card, Dropdown, Icon, Rail} from "semantic-ui-react";
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

const VacancyDetails: React.FC<VacancyDetailsProps & RouteComponentProps<UrlParams>> = (
    {
        vacancy,
        loadVacancy,
        applyVacancy,
        match
    }) => {
    useEffect(() => {
        loadVacancy(match.params.vacancyId)
    }, [match.params.vacancyId])

    const isCreatedByCurrentUser = vacancy?.recruiter.id === getUserId()

    const getApplyButton = () => {
        if (!isCreatedByCurrentUser) {
            const button = vacancy?.applied
                ? <Button disabled={true} positive>Applied</Button>
                : <Button onClick={() => applyVacancy(match.params.vacancyId)} primary>Apply</Button>

            return <Rail position="right">
                {button}
            </Rail>
        } else {
            return <></>
        }
    }

    return (
        <Card className={styles.vacancy}>
            <Card.Content>
                <Card.Header>{vacancy?.title}</Card.Header>
                {isCreatedByCurrentUser &&
                    <>
                        <Dropdown className={`${styles.dropdown}`} icon="ellipsis vertical">
                            <Dropdown.Menu>
                                <Dropdown.Item>Edit</Dropdown.Item>
                                <Dropdown.Item>Delete</Dropdown.Item>
                            </Dropdown.Menu>
                        </Dropdown>
                        <Rail position="right">
                            <NavLink to={withVacancyId(links.candidatesForVacancy, vacancy?.id)}>
                                <Button primary className={styles.viewCandidates}>View candidates</Button>
                            </NavLink>
                        </Rail>
                    </>
                }
            </Card.Content>
            <Card.Content className={styles.description}
                          description={vacancy?.description}/>
            <Card.Content extra>
                <Icon name='user'/> {vacancy?.candidatesCount}
            </Card.Content>
            {getApplyButton()}

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