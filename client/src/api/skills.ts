import {AxiosResponse} from "axios";

import {ISkill} from "../models/skills";
import {client} from "./index";

export const skills = {
    loadAllExistingSkills() {
        return client.get(`/skills`)
            .then((r: AxiosResponse<ISkill[]>) => r.data)
    }
}