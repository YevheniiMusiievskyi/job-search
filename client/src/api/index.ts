import axios from "axios";
import {users} from "./users";
import {posts} from "./posts";
import {comments} from "./comments";
import {images} from "./images";
import {auth} from "./auth";

export const client = axios.create();
client.defaults.baseURL = "/api";

export const API = {
    auth,
    users,
    posts,
    comments,
    images
};
