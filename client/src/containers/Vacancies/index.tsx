import React from "react";
import InfiniteScroll from "react-infinite-scroller";
import {IRootState} from "../../store";
import {loadMoreVacancies, loadVacancies} from "../../store/actions/vacancies";
import {connect, ConnectedProps} from "react-redux";
import {Button, Loader} from "semantic-ui-react";
import {IVacancyShort} from "../../models/vacancies";
import {VacancyShort} from "../../components/VacancyShort";
import styles from "./styles.module.scss"
import {NavLink} from "react-router-dom";
import {links} from "../../helpers/links";
import {isRecruiter} from "../../helpers/tokenParser";

const vacanciesFilter = {
    page: 0,
    size: 25
}

const Vacancies: React.FC<VacanciesProps> = ({vacancies, hasMoreVacancies, loadVacancies, loadMoreVacancies}) => {

    const getMoreVacancies = () => {
        vacanciesFilter.page === 0 ? loadVacancies(vacanciesFilter) : loadMoreVacancies(vacanciesFilter);
        vacanciesFilter.page++;
    }

    return (
        <div>
            {isRecruiter() &&
            <NavLink to={links.createVacancy}>
                <Button className={styles.createNewVacancy} primary floated="right">Create vacancy</Button>
            </NavLink>
            }
            <InfiniteScroll loadMore={getMoreVacancies} hasMore={hasMoreVacancies}
                            loader={<Loader active inline="centered" key={0}/>}>
                {
                    vacancies.map((v: IVacancyShort) => {
                        return <VacancyShort vacancy={v}/>
                    })
                }
            </InfiniteScroll>
        </div>
    );
}

const mapStateToProps = (state: IRootState) => ({
    vacancies: state.vacancies.vacancies,
    hasMoreVacancies: state.vacancies.hasMoreVacancies
})

const mapDispatchToProps = {
    loadVacancies,
    loadMoreVacancies
}

const connector = connect(
    mapStateToProps,
    mapDispatchToProps
);

type VacanciesProps = ConnectedProps<typeof connector>;

export default connector(Vacancies);