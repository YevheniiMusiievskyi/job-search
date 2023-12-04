import axios from "axios";
import {users} from "./users";
import {posts} from "./posts";
import {comments} from "./comments";
import {images} from "./images";
import {auth} from "./auth";
import {userProfile} from "./userProfile";
import {skills} from "./skills";
import {vacancies} from "./vacancies";

export const client = axios.create();
client.defaults.baseURL = "/api";

export const API = {
    auth,
    users,
    userProfile,
    skills,
    posts,
    comments,
    vacancies,
    images
};
