import {IRootState} from "../../store";
import {getCandidates} from "../../store/actions/candidates";
import {connect, ConnectedProps} from "react-redux";
import React from "react";
import InfiniteScroll from "react-infinite-scroller";
import {IPageFiler} from "../../models/filter";

const filter: IPageFiler = {
    page: 0,
    size: 25
}

const Candidates: React.FC<CandidatesProps> = ({ candidates, hasMoreCandidates, getCandidates }) => {

    const loadMoreCandidates = () => {
        getCandidates(filter)
        filter.page++
    }

    return (
        <InfiniteScroll pageStart={0} hasMore={hasMoreCandidates} loadMore={loadMoreCandidates}>
            
        </InfiniteScroll>
    )
}

const mapStateToProps = (state: IRootState) => ({
    candidates: state.candidates.candidates,
    hasMoreCandidates: state.candidates.hasMoreCandidates
})

const mapDispatchToProps = {
    getCandidates
}

const connector = connect(mapStateToProps, mapDispatchToProps)

type CandidatesProps = ConnectedProps<typeof connector>

export default connector(Candidates)