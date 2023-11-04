import {client} from "./index";
import {IAuthUser, IRegistrationRequest} from "../models/auth";
import {AxiosResponse} from "axios";

export const auth = {
    login(request: FormData): Promise<IAuthUser> {
        return client.post("/auth/login", request)
            .then((r: AxiosResponse<IAuthUser>) => r.data);
    },
    register(request: IRegistrationRequest): Promise<IAuthUser> {
        return client.post("/auth/register", request)
            .then((r: AxiosResponse<IAuthUser>) => r.data);
    }
};