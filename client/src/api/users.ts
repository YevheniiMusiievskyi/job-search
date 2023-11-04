import {client} from "./index";
import {AxiosResponse} from "axios";
import {IUser} from "../models/auth";
import {IImage} from "../models/images";

export const users = {
    fetchCurrent(): Promise<IUser> {
        return client.get("/user")
            .then((r: AxiosResponse<IUser>) => r.data);
    },
    setUserAvatar(avatar: IImage): Promise<IUser> {
        return client.post("/user/avatar", avatar);
    }
};
