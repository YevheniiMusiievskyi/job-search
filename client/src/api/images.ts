import {client} from "./index";
import {AxiosResponse} from "axios";
import {IImage} from "../models/images";

export const images = {
    upload(image: File): Promise<IImage> {
        const formData = new FormData();
        formData.append("file", image);
        return client.post("/images", formData, {
            headers: {
                'Content-Type': 'multipart/form-data'
            }
        }
        ).then((r: AxiosResponse<IImage>) => r.data);
    }
};
