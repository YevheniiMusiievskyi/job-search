import {IVacancy, IVacancyCreation, IVacancyShort, IVacancyUpdate} from "../models/vacancies";
import {client} from "./index";
import {AxiosResponse} from "axios";

const vacanciesApi = '/vacancies';

export const vacancies = {
    fetchList(page?: number, size?: number): Promise<IVacancyShort[]> {
        return client.get(`${vacanciesApi}?page=${page}&size=${size}`)
            .then((r: AxiosResponse<IVacancyShort[]>) => r.data)
    },
    fetch(id: string): Promise<IVacancy> {
        return client.get(`${vacanciesApi}/${id}`)
            .then((r: AxiosResponse<IVacancy>) => r.data)
    },
    create(vacancy: IVacancyCreation): Promise<void> {
        return client.post(`${vacanciesApi}`, vacancy)
    },
    update(id: string, vacancy: IVacancyUpdate): Promise<void> {
        return client.put(`${vacanciesApi}/${id}`, vacancy)
    },
    applyVacancy(id: string): Promise<void> {
        return client.post(`${vacanciesApi}/apply/${id}`)
    }
}