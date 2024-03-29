import {IUser} from "./auth";

export interface IVacancy {
    id: string;
    title: string;
    description: string;
    candidatesCount: number;
    recruiter: IUser
    applied: boolean;
}

export interface IVacancyShort {
    id: string;
    title: string;
    description: string;
    candidatesCount: number;
}

export interface IVacancyCreation {
    title: string;
    description: string;
}

export interface IVacancyUpdate {
    title: string;
    description: string;
}