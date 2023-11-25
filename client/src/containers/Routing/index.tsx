import React, {useEffect} from 'react';
import {Route, Switch} from 'react-router-dom';
import {connect, ConnectedProps} from 'react-redux';
import Thread from 'src/containers/Thread';
import LoginPage from 'src/containers/LoginPage';
import RegistrationPage from 'src/containers/RegistrationPage';
import Profile from 'src/containers/Profile';
import Header from 'src/components/Header';
import Spinner from 'src/components/Spinner';
import NotFound from 'src/scenes/NotFound';
import PrivateRoute from 'src/containers/PrivateRoute';
import PublicRoute from 'src/containers/PublicRoute';
import {IRootState} from "src/store";
import {loadCurrentUser, logout} from "../../store/actions/users";

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
                                <Header user={user} logout={logout} />
                            </header>
                        )}
                        <main className="fill">
                            <Switch>
                                <PublicRoute exact path="/login" component={LoginPage}/>
                                <PublicRoute exact path="/registration" component={RegistrationPage}/>
                                <PrivateRoute
                                    exact
                                    path={[
                                        "/",
                                        "/post/:postId",
                                        "/user/:userId",
                                        "/user/:userId/post/:postId"]
                                    }
                                    component={Thread}
                                />
                                <PrivateRoute exact path="/profile/:userId" component={Profile}/>
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
