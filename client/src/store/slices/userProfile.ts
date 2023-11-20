import {createSlice} from "@reduxjs/toolkit";
import {IUserProfile} from "../../models/userProfile";

interface InitialState {
    userProfile: IUserProfile | null;
    isAuthorized: boolean;
    isLoading: boolean;
}

const initialState: InitialState = {
    userProfile: null,
    isAuthorized: false,
    isLoading: false
};

export const userProfile = createSlice({
    name: "userProfile",
    initialState,
    reducers: {
        setProfile(state, { payload: profile }) {
            state.userProfile = profile;
        }
    }
});

export default userProfile.reducer;