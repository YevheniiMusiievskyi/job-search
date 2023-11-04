import {createSlice} from "@reduxjs/toolkit";
import {IUser} from "../../models/auth";

interface InitialState {
    user: IUser | null;
    isAuthorized: boolean;
    isLoading: boolean;
}

const initialState: InitialState = {
    user: null,
    isAuthorized: false,
    isLoading: false
};

export const users = createSlice({
    name: "users",
    initialState,
    reducers: {
        setUser(state, { payload: user }) {
            state.user = user;
            state.isAuthorized = Boolean(user?.id);
            state.isLoading = false;
        },
        setAvatar(state, { payload: avatar }) {
            if (state.user) {
                state.user.avatar = avatar;
            }
        }
    }
});

export default users.reducer;