import {Dispatch} from "redux";
import {API} from "../../api";
import {IContacts, IUserProfile, IUserProfilePostRequest} from "../../models/userProfile";
import {userProfile} from "../slices/userProfile";

const {actions} = userProfile;

export const loadUserProfile = (userId: string) => (dispatch: Dispatch) => {
    return API.userProfile.fetch(userId)
        .then((p: IUserProfile) => dispatch(actions.setUserProfile(p)))
}

export const createUserProfile = (userProfile: IUserProfilePostRequest) => (dispatch: Dispatch) => {
    return API.userProfile.create(userProfile)
        .then((p: IUserProfile) => dispatch(actions.setProfile(p)))
}

export const updateProfile = (profile: IUserProfilePostRequest) => (dispatch: Dispatch) => {
    return API.userProfile.updateProfile(profile)
        .then((p: IUserProfile) => dispatch(actions.setProfile(p)))
}

export const updateContacts = (contacts: IContacts) => (dispatch: Dispatch) => {
    return API.userProfile.updateContacts(contacts)
        .then((c: IContacts) => dispatch(actions.setContacts(c)))
}
