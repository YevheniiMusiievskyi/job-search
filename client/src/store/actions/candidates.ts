import {IPageFiler} from "../../models/filter";
import {IUserProfile} from "../../models/userProfile";
import {Dispatch} from "redux";
import {API} from "../../api";
import {candidates} from "../slices/candidates";

const { actions } = candidates;

export const getCandidates = (filter: IPageFiler) => (dispatch: Dispatch) => {
    API.userProfile.getCandidates(filter.page, filter.size)
        .then(c => dispatch(actions.loadMoreCandidates(c)))
}