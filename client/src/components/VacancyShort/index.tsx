import {IVacancyShort} from "../../models/vacancies";
import React from "react";
import {Card, Icon} from "semantic-ui-react";
import {trimToSize} from "../../helpers/utils";
import styles from "./styles.module.scss"

interface IVacancyShortProps {
    vacancy: IVacancyShort
}

const maxDescriptionLength = 300;

export const VacancyShort: React.FC<IVacancyShortProps> = ({vacancy}) => {
    return (
        <Card className={styles.vacancy} href={`/vacancies/${vacancy.id}`}>
            <Card.Content header={vacancy.title} />
            <Card.Content className={styles.description}
                          description={trimToSize(vacancy.description, maxDescriptionLength)}/>
            <Card.Content extra>
                <Icon name='user'/> {vacancy.candidatesCount}
            </Card.Content>
        </Card>
    )
}