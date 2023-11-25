import {ISkill, ISkillPostRequest} from "./skills";

export interface IUserProfile {
    id: string;
    title: string | null;
    description: string | null;
    skills: ISkill[];
}

export interface IUserProfilePostRequest {
    id: string | null;
    title: string;
    description: string;
    skills: ISkillPostRequest[];
}

