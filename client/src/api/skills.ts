import axios, {AxiosResponse} from "axios";

import {ISkill} from "../models/skills";

export const skills = {
    loadAllExistingSkills() {
        return axios.get(`/user-profile/skills`)
            .then((r: AxiosResponse<ISkill[]>) => r.data)
    }
}