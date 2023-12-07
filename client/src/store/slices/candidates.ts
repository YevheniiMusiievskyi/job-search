import {IUserProfile} from "../../models/userProfile";
import {createSlice} from "@reduxjs/toolkit";

interface InitialState {
    candidates: IUserProfile[];
    hasMoreCandidates: boolean;
}

const initialState: InitialState = {
    candidates: [],
    hasMoreCandidates: true
}

export const candidates = createSlice({
    name: "candidates",
    initialState,
    reducers: {
        loadMoreCandidates(state, { payload: candidates }) {
            state.candidates.concat(candidates)
            state.hasMoreCandidates = Boolean(candidates?.length)
        }
    }
})

export default candidates.reducer;