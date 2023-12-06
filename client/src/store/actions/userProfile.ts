import {Dispatch} from "redux";
import {API} from "../../api";
import {IUserProfile, IUserProfilePostRequest} from "../../models/userProfile";
import { userProfile } from "../slices/userProfile";
const { actions } = userProfile;

export const loadUserProfile = (userId: string) => (dispatch: Dispatch) => {
    return API.userProfile.fetch(userId)
        .then((p: IUserProfile) => dispatch(actions.setProfile(p)))
}

export const createUserProfile = (userProfile: IUserProfilePostRequest) => (dispatch: Dispatch) => {
    return API.userProfile.create(userProfile)
        .then((p: IUserProfile) => dispatch(actions.setProfile(p)))
}

export const updateUserProfile = (userProfile: IUserProfilePostRequest) => (dispatch: Dispatch) => {
    return API.userProfile.update(userProfile)
        .then((p: IUserProfile) => dispatch(actions.setProfile(p)))
}