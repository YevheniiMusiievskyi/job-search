import {API} from "../../api";
import {Dispatch} from "redux";
import {users} from "../slices/users";

const { actions } = users;

export const uploadImage = (file: File) =>
    API.images.upload(file)
        .then(r => r);

export const uploadAvatar = (file: File) => (dispatch: Dispatch) =>
    API.images.upload(file)
        .then(r => {
            dispatch(actions.setAvatar(r))
            API.users.setUserAvatar(r);
        });