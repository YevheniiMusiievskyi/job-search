import {createSlice} from "@reduxjs/toolkit";
import {IVacancy, IVacancyShort} from "../../models/vacancies";

interface InitialState {
    vacancies: IVacancyShort[];
    hasMoreVacancies: boolean;
    expandedVacancy: IVacancy | null;
}

const initialState: InitialState = {
    vacancies: [],
    hasMoreVacancies: true,
    expandedVacancy: null
};

export const vacancies = createSlice({
    name: "vacancies",
    initialState,
    reducers: {
        setAllVacancies(state, { payload: vacancies }) {
            state.vacancies = vacancies
            state.hasMoreVacancies = Boolean(vacancies?.length);
        },
        loadMoreVacancies(state, { payload: vacancies }) {
            state.vacancies = state.vacancies.concat(vacancies);
            state.hasMoreVacancies = Boolean(vacancies?.length);
        },
        setExpandedVacancy(state, { payload: vacancy }) {
            state.expandedVacancy = vacancy
        },
        setVacancyApplied(state) {
            if (state.expandedVacancy) {
                state.expandedVacancy = {
                    ...state.expandedVacancy,
                    applied: true
                }
            }
        }
    }
});

export default vacancies.reducer;