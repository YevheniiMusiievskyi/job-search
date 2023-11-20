import {ISkill} from "./skills";

export interface IUserProfile {
    title: string | null;
    description: string | null;
    skills: ISkill[];
}

