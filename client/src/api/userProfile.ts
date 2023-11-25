import {IUserProfile, IUserProfilePostRequest} from "../models/userProfile";
import {AxiosResponse} from "axios";
import {client} from "./index";

export const userProfile = {
    fetch(userId: string): Promise<IUserProfile> {
        return client.get(`/user-profile/${userId}`)
            .then((r: AxiosResponse<IUserProfile>) => r.data);
    },
    update(userProfile: IUserProfilePostRequest): Promise<IUserProfile> {
       return client.post("/user-profile", userProfile)
           .then((r: AxiosResponse<IUserProfile>) => r.data);
    }
}