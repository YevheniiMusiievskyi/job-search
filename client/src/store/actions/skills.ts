import {Dispatch} from "redux";
import {API} from "../../api";
import {ISkill} from "../../models/skills";
import {skills} from "../slices/skills";

const { actions } = skills;

export const loadAllExistingSkills = () => (dispatch: Dispatch) => {
    return API.skills.loadAllExistingSkills()
        .then((skills: ISkill[]) => dispatch(actions.setAllExistingSkills(skills)))
}