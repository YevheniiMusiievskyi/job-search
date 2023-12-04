import React from 'react';
import {Route, Redirect, useLocation} from 'react-router-dom';
import PropTypes from 'prop-types';
import {connect} from 'react-redux';
import {IRootState} from "src/store";
import styles from './styles.module.scss';

const PrivateRoute = ({component: Component, isAuthorized, ...rest}) => {
    const location = useLocation();

    return (
        <Route
            {...rest} render={props => (isAuthorized
            ? (
                <div className={styles.content}>
                    <Component {...props} />
                </div>
            )
            : <Redirect to={{pathname: '/login', state: { from: location.pathname }}}/>)}/>
    )
};

PrivateRoute.propTypes = {
    isAuthorized: PropTypes.bool,
    component: PropTypes.any.isRequired, // eslint-disable-line
    location: PropTypes.any // eslint-disable-line
};

PrivateRoute.defaultProps = {
    isAuthorized: false,
    location: undefined
};

const mapStateToProps = (state: IRootState) => ({
    isAuthorized: state.profile.isAuthorized
});

export default connect(mapStateToProps)(PrivateRoute);
