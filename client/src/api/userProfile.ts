import {IUserProfile, IUserProfilePostRequest} from "../models/userProfile";
import {AxiosResponse} from "axios";
import {client} from "./index";

const path = "/user-profile"

export const userProfile = {
    fetch(userId: string): Promise<IUserProfile> {
        return client.get(`${path}/${userId}`)
            .then((r: AxiosResponse<IUserProfile>) => r.data);
    },
    create(userProfile: IUserProfilePostRequest): Promise<IUserProfile> {
       return client.post(path, userProfile)
           .then((r: AxiosResponse<IUserProfile>) => r.data);
    },
    update(userProfile: IUserProfilePostRequest): Promise<IUserProfile> {
        return client.put(path, userProfile)
            .then((r: AxiosResponse<IUserProfile>) => r.data);
    },
    getCandidates(page, size): Promise<IUserProfile[]> {
        return client.get(`${path}/candidates?page=${page}&size=${size}`)
            .then((r: AxiosResponse<IUserProfile[]>) => r.data)
    }
}