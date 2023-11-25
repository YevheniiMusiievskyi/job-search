import {ISkill, ISkillPostRequest, ITag} from "../../models/skills";
import {isUUID} from "../utils";

export function mapTagToSkill(tag: ITag): ISkill {
    return {
        id: tag.id,
        name: tag.text
    }
}

export function mapTagToSkillPostRequest(tag: ITag): ISkillPostRequest {
    return {
        id: isUUID(tag.id) ? tag.id : null,
        name: tag.text
    }
}

export function mapSkillToTag(skill: ISkill): ITag {
    return {
        id: skill.id,
        text: skill.name
    }
}