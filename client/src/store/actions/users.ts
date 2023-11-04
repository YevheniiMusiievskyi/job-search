import {Dispatch} from "redux";
import {API} from "../../api";
import {users} from "../slices/users";
import {IAuthUser, IRegistrationRequest, IUser} from "../../models/auth";
import {deleteCookie} from "../../helpers/cookiesHelper";

const actions = users.actions;

export const loadCurrentUser = () => (dispatch: Dispatch) =>
    API.users.fetchCurrent()
        .then((u: IUser) => dispatch(actions.setUser(u)));

export const login = (request: FormData) => (dispatch: Dispatch) =>
    API.auth.login(request)
        .then((r: IAuthUser) => handleAuthResponse(r)(dispatch));

export const register = (request: IRegistrationRequest) => (dispatch: Dispatch) =>
    API.auth.register(request)
        .then((r: IAuthUser) => handleAuthResponse(r)(dispatch));

export const logout = () => (dispatch: Dispatch) => {
    setAuthData(null);
    dispatch(actions.setUser(null));
}

const handleAuthResponse = (r: IAuthUser) => (dispatch: Dispatch) => {
    setAuthData(r.token);
    dispatch(actions.setUser(r.user));
};

export const setAuthData = (token: string | null) => {
    if (token) {
        document.cookie = `Authorization=${token}`;
    } else {
        deleteCookie("Authorization");
    }
}
