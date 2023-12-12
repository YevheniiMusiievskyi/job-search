import {IRootState} from "../../store";
import {loadCandidates} from "../../store/actions/candidates";
import {connect, ConnectedProps} from "react-redux";
import React from "react";
import InfiniteScroll from "react-infinite-scroller";
import {IPageFiler} from "../../models/filter";
import CandidateShortView from "../../components/CandidateShortView";

const filter: IPageFiler = {
    page: 0,
    size: 25
}

const Candidates: React.FC<CandidatesProps> = ({candidates, hasMoreCandidates, loadCandidates}) => {

    const loadMoreCandidates = () => {
        loadCandidates(filter)
        filter.page++
    }

    return (
        <InfiniteScroll pageStart={0} hasMore={hasMoreCandidates} loadMore={loadMoreCandidates}>
            {candidates.map(candidate => {
                return <CandidateShortView userProfile={candidate} userId={candidate.userId}/>
            })}
        </InfiniteScroll>
    )
}

const mapStateToProps = (state: IRootState) => ({
    candidates: state.candidates.candidates,
    hasMoreCandidates: state.candidates.hasMoreCandidates
})

const mapDispatchToProps = {
    loadCandidates
}

const connector = connect(mapStateToProps, mapDispatchToProps)

type CandidatesProps = ConnectedProps<typeof connector>

export default connector(Candidates)