import {Dispatch} from "redux";
import {API} from "../../api";
import {IVacancy, IVacancyCreation, IVacancyShort} from "../../models/vacancies";
import {vacancies} from "../slices/vacancies";
const { actions } = vacancies;

export const loadVacancies = filter => (dispatch: Dispatch) => {
    return API.vacancies.fetchList(filter.page, filter.size)
        .then((v: IVacancyShort[]) => dispatch(actions.setAllVacancies(v)))
}

export const loadMoreVacancies = filter => (dispatch: Dispatch) => {
    return API.vacancies.fetchList(filter.page, filter.size)
        .then((v: IVacancyShort[]) => dispatch(actions.loadMoreVacancies(v)))
}

export const loadVacancy = (vacancyId: string) => (dispatch: Dispatch) => {
    return API.vacancies.fetch(vacancyId)
        .then((v: IVacancy) => dispatch(actions.setExpandedVacancy(v)))
}

export const createVacancy = (vacancy: IVacancyCreation) => {
    return API.vacancies.create(vacancy)
}

export const applyVacancy = (vacancyId: string) => (dispatch: Dispatch) => {
    return API.vacancies.applyVacancy(vacancyId)
        .then(() => dispatch(actions.setVacancyApplied()))
}