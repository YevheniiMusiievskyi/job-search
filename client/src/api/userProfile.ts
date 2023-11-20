import {IUserProfile} from "../models/userProfile";
import axios, {AxiosResponse} from "axios";
import {ISkill} from "../models/skills";

export const userProfile = {
    fetch(userId: string): Promise<IUserProfile> {
        return axios.get(`/user-profile/${userId}`)
            .then((r: AxiosResponse<IUserProfile>) => r.data);
    },
    update(userProfile: IUserProfile): Promise<IUserProfile> {
       return axios.post("/user-profile", userProfile)
           .then((r: AxiosResponse<IUserProfile>) => r.data);
    }
}