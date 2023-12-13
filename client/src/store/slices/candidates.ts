import {IUserProfile} from "../../models/userProfile";
import {createSlice} from "@reduxjs/toolkit";

interface InitialState {
    candidates: IUserProfile[];
    hasMoreCandidates: boolean;
    expandedCandidate: IUserProfile | null;
}

const initialState: InitialState = {
    candidates: [],
    hasMoreCandidates: true,
    expandedCandidate: null
}

export const candidates = createSlice({
    name: "candidates",
    initialState,
    reducers: {
        setAllCandidates(state, { payload: candidates }) {
            state.candidates = candidates
            state.hasMoreCandidates = Boolean(candidates?.length)
        },
        loadMoreCandidates(state, { payload: candidates }) {
            state.candidates = state.candidates.concat(candidates)
            state.hasMoreCandidates = Boolean(candidates?.length)
        },
        setExpandedCandidate(state, { payload: candidate }) {
            state.expandedCandidate = candidate
        }
    }
})

export default candidates.reducer;