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
        setUserProfile(state, { payload: userProfile }) {
            state.userProfile = userProfile
        },
        setProfile(state, { payload: profile }) {
            if (state.userProfile) {
                state.userProfile.profile = profile
            }
        },
        setContacts(state, { payload: contacts }) {
            if (state.userProfile) {
                state.userProfile.contacts = contacts
            }
        }
    }
});

export default userProfile.reducer;