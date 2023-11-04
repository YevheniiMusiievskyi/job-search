import {IUser} from "./auth";

export interface IProfile {
    user: IUser;
    isAuthorized: boolean;
    isLoading: boolean;
}
