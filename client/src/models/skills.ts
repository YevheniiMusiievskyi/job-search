export interface ISkill {
    id: string;
    name: string;
}

export interface ISkillPostRequest {
    id: string | null;
    name: string;
}

export interface ITag {
    id: string;
    text: string;
}