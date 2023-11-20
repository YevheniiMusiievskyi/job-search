import {Dispatch} from "redux";
import {API} from "../../api";
import {IUserProfile} from "../../models/userProfile";
import { userProfile } from "../slices/userProfile";
import {ISkill} from "../../models/skills";
const { actions } = userProfile;

export const loadUserProfile = (userId: string) => (dispatch: Dispatch) => {
    return API.userProfile.fetch(userId)
        .then((p: IUserProfile) => dispatch(actions.setProfile(p)))
}