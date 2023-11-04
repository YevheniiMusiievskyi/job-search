import React from 'react';
import {Route, Redirect} from 'react-router-dom';
import PropTypes from 'prop-types';
import {connect} from 'react-redux';
import {IRootState} from "src/store";

const PublicRoute = ({component: Component, isAuthorized, ...rest}) => {
    const fromUrl = rest.location.state?.from;
    const pathname = fromUrl ? fromUrl : "/";

    return (
        <Route
            {...rest} render={props => (isAuthorized
            ? <Redirect to={{pathname: pathname, state: {from: props.location}}}/>
            : <Component {...props} />)}/>
    )
};

PublicRoute.propTypes = {
    isAuthorized: PropTypes.bool,
    component: PropTypes.any.isRequired, // eslint-disable-line
    location: PropTypes.any // eslint-disable-line
};

PublicRoute.defaultProps = {
    isAuthorized: false,
    location: undefined
};

const mapStateToProps = (state: IRootState) => ({
    isAuthorized: state.profile.isAuthorized
});

export default connect(mapStateToProps)(PublicRoute);
