import {IContacts, IUserProfile, IUserProfilePostRequest} from "../models/userProfile";
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
    updateProfile(userProfile: IUserProfilePostRequest): Promise<IUserProfile> {
        return client.put(`${path}/profile`, userProfile)
            .then((r: AxiosResponse<IUserProfile>) => r.data);
    },
    updateContacts(contacts: IContacts): Promise<IContacts> {
        return client.put(`${path}/contacts`, contacts)
            .then((r: AxiosResponse<IContacts>) => r.data);
    },
    fetchCandidates(page, size): Promise<IUserProfile[]> {
        return client.get(`${path}/candidates?page=${page}&size=${size}`)
            .then((r: AxiosResponse<IUserProfile[]>) => r.data)
    },
    fetchCandidate(id: string): Promise<IUserProfile> {
        return client.get(`${path}/candidates/${id}`)
            .then((r: AxiosResponse<IUserProfile>) => r.data)
    }
}