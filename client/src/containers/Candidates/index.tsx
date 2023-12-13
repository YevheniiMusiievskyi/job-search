import {IRootState} from "../../store";
import {
    loadCandidates,
    loadCandidatesForVacancy,
    loadMoreCandidates,
    loadMoreCandidatesForVacancy
} from "../../store/actions/candidates";
import {connect, ConnectedProps} from "react-redux";
import React, {useEffect} from "react";
import InfiniteScroll from "react-infinite-scroller";
import {IPageFiler} from "../../models/filter";
import CandidateShortView from "../../components/CandidateShortView";
import {RouteComponentProps} from "react-router-dom";

const filter: IPageFiler = {
    page: 0,
    size: 25
}

interface UrlParams {
    vacancyId: string | undefined
}

const Candidates: React.FC<CandidatesProps & RouteComponentProps<UrlParams>> = (
    {
        candidates,
        hasMoreCandidates,
        loadCandidates,
        loadMoreCandidates,
        loadCandidatesForVacancy,
        loadMoreCandidatesForVacancy,
        match
    }) => {
    const vacancyId = match.params.vacancyId

    const getMoreCandidates = () => {
        if (vacancyId) {
            filter.page === 0
                ? loadCandidatesForVacancy(vacancyId, filter)
                : loadMoreCandidatesForVacancy(vacancyId, filter)
        } else {
            filter.page === 0 ? loadCandidates(filter) : loadMoreCandidates(filter)
        }
        filter.page++
    }

    useEffect(() => {
        window.scrollTo(0, 0)
        filter.page = 0
        getMoreCandidates()
    }, [vacancyId])

    return (
        <InfiniteScroll pageStart={0} hasMore={hasMoreCandidates} loadMore={getMoreCandidates}>
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
    loadCandidates,
    loadMoreCandidates,
    loadCandidatesForVacancy,
    loadMoreCandidatesForVacancy
}

const connector = connect(mapStateToProps, mapDispatchToProps)

type CandidatesProps = ConnectedProps<typeof connector>

export default connector(Candidates)