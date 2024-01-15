import {ISkill, ISkillPostRequest} from "./skills";

export interface IUserProfile {
    id: string
    userId: string
    profile: IProfile | null
    contacts: IContacts | null
}

export interface IProfile {
    title: string | null;
    description: string | null;
    skills: ISkill[];
}

export interface IContacts {
    avatar: string | null;
    fullName: string | null;
    email: string | null;
    phone: string | null;
}

export interface IContactsPutRequest {
    fullName: string | null;
    email: string | null;
    phone: string | null;
}

export interface IUserProfilePostRequest {
    title: string;
    description: string;
    skills: ISkillPostRequest[];
}

