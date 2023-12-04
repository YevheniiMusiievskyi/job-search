import React, {useEffect} from "react";
import styles from "../../components/VacancyShort/styles.module.scss";
import {Button, Card, Icon} from "semantic-ui-react";
import {IRootState} from "../../store";
import {connect, ConnectedProps} from "react-redux";
import {RouteComponentProps} from "react-router-dom";
import {applyVacancy, loadVacancy} from "../../store/actions/vacancies";

interface UrlParams {
    vacancyId: string;
}

const VacancyDetails: React.FC<VacancyDetailsProps & RouteComponentProps<UrlParams>> = ({vacancy, loadVacancy, applyVacancy, match}) => {
    useEffect(() => {
        loadVacancy(match.params.vacancyId)
    }, [match.params.vacancyId])

    return (
        <Card className={styles.vacancy}>
            <Card.Content header={vacancy?.title}/>
            <Card.Content className={styles.description}
                          description={vacancy?.description}/>
            <Card.Content extra>
                <Icon name='user'/> {vacancy?.candidatesCount}
            </Card.Content>
            {vacancy?.isApplied
                ? <Button disabled={true} primary>Applied</Button>
                : <Button onClick={() => applyVacancy(match.params.vacancyId)} primary>Apply</Button>
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