import {ISkill} from "../../models/skills";
import {createSlice} from "@reduxjs/toolkit";

interface InitialState {
    existingSkills: ISkill[] | null;
}

const initialState: InitialState = {
    existingSkills: null
};

export const skills = createSlice({
    name: "skills",
    initialState,
    reducers: {
        setAllExistingSkills(state, { payload: skills }) {
            state.existingSkills = skills;
        }
    }
});

export default skills.reducer;
