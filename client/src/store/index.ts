import {
    createStore,
    applyMiddleware,
    compose,
    combineReducers
} from 'redux';
import {connectRouter, routerMiddleware} from 'connected-react-router';
import thunk, {ThunkMiddleware} from 'redux-thunk';
import {createBrowserHistory} from 'history';

import {thread} from "./slices/thread";
import {users} from "./slices/users";
import {userProfile} from "./slices/userProfile";
import {skills} from "./slices/skills";
import {vacancies} from "./slices/vacancies";
import {candidates} from "./slices/candidates";

export const history = createBrowserHistory();

const middlewares = [
    thunk as ThunkMiddleware,
    routerMiddleware(history)
];

declare global {
    interface Window {
        __REDUX_DEVTOOLS_EXTENSION_COMPOSE__?: typeof compose;
    }
}

const composedEnhancers = window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__ || compose;

const reducers = {
    posts: thread.reducer,
    profile: users.reducer,
    userProfile: userProfile.reducer,
    vacancies: vacancies.reducer,
    candidates: candidates.reducer,
    skills: skills.reducer
};

const rootReducer = combineReducers({
    router: connectRouter(history),
    ...reducers
});

export type IRootState = ReturnType<typeof rootReducer>

const store = createStore(
    rootReducer,
    composedEnhancers(applyMiddleware(...middlewares))
);

export default store;
