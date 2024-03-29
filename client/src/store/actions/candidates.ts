import {IPageFiler} from "../../models/filter";
import {IUserProfile} from "../../models/userProfile";
import {Dispatch} from "redux";
import {API} from "../../api";
import {candidates} from "../slices/candidates";

const { actions } = candidates;

export const loadCandidates = (filter: IPageFiler) => (dispatch: Dispatch) => {
    API.userProfile.fetchCandidates(filter.page, filter.size)
        .then(c => dispatch(actions.setAllCandidates(c)))
}

export const loadMoreCandidates = (filter: IPageFiler) => (dispatch: Dispatch) => {
    API.userProfile.fetchCandidates(filter.page, filter.size)
        .then(c => dispatch(actions.loadMoreCandidates(c)))
}

export const loadCandidatesForVacancy = (vacancyId: string, filter: IPageFiler) => (dispatch: Dispatch) => {
    API.userProfile.fetchCandidatesForVacancy(vacancyId, filter.page, filter.size)
        .then(c => dispatch(actions.setAllCandidates(c)))
}

export const loadMoreCandidatesForVacancy = (vacancyId: string, filter: IPageFiler) => (dispatch: Dispatch) => {
    API.userProfile.fetchCandidatesForVacancy(vacancyId, filter.page, filter.size)
        .then(c => dispatch(actions.loadMoreCandidates(c)))
}

export const loadCandidate = (id: string) => (dispatch: Dispatch) => {
    API.userProfile.fetchCandidate(id)
        .then(c => dispatch(actions.setExpandedCandidate(c)))
}