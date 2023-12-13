import React, {useEffect} from 'react';
import {Route, Switch} from 'react-router-dom';
import {connect, ConnectedProps} from 'react-redux';
import Thread from 'src/containers/Thread';
import LoginPage from 'src/containers/LoginPage';
import RegistrationPage from 'src/containers/RegistrationPage';
import UserProfile from 'src/containers/UserProfile';
import Header from 'src/components/Header';
import Spinner from 'src/components/Spinner';
import NotFound from 'src/scenes/NotFound';
import PrivateRoute from 'src/containers/PrivateRoute';
import PublicRoute from 'src/containers/PublicRoute';
import {IRootState} from "src/store";
import {loadCurrentUser, logout} from "../../store/actions/users";
import Vacancies from "../Vacancies";
import VacancyCreation from "../VacancyCreation";
import VacancyDetails from "../VacancyDetails";
import {links} from "../../helpers/links";
import Candidates from "../Candidates";
import Candidate from "../Candidate";

const Routing: React.FC<RoutingProps> =
    ({
         user,
         isAuthorized,
         loadCurrentUser,
         logout,
         isLoading
     }) => {
        useEffect(() => {
            if (!isAuthorized) {
                loadCurrentUser();
            }
        });

        return (
            isLoading
                ? <Spinner/>
                : (
                    <div className="fill">
                        {isAuthorized && (
                            <header>
                                <Header user={user} logout={logout}/>
                            </header>
                        )}
                        <main className="fill">
                            <Switch>
                                <PublicRoute exact path={links.login} component={LoginPage}/>
                                <PublicRoute exact path={links.registration} component={RegistrationPage}/>
                                <PrivateRoute
                                    exact
                                    path={[
                                        links.home,
                                        links.expandedPost,
                                        links.userPosts,
                                        links.userPostsExpanded]
                                    }
                                    component={Thread}
                                />
                                <PrivateRoute exact path={links.userProfile} component={UserProfile}/>
                                <PrivateRoute exact path={links.vacancies} component={Vacancies}/>
                                <PrivateRoute exact path={links.vacancyDetails} component={VacancyDetails}/>
                                <PrivateRoute exact path={links.createVacancy} component={VacancyCreation}/>
                                <PrivateRoute
                                    exact
                                    path={[
                                        links.candidates,
                                        links.candidatesForVacancy]
                                    }
                                    component={Candidates}
                                />
                                <PrivateRoute exact path={links.candidate} component={Candidate}/>
                                <Route path="*" exact component={NotFound}/>
                            </Switch>
                        </main>
                    </div>
                )
        );
    };

const mapStateToProps = ({profile}: IRootState) => ({
    isAuthorized: profile.isAuthorized,
    user: profile.user,
    isLoading: profile.isLoading
});

const mapDispatchToProps = {
    loadCurrentUser,
    logout
};

const connector = connect(
    mapStateToProps,
    mapDispatchToProps
);

type RoutingProps = ConnectedProps<typeof connector>;

export default connector(Routing);
