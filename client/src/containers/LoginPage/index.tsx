import React from 'react';
import {connect, ConnectedProps} from 'react-redux';
import {Grid, Header, Message} from 'semantic-ui-react';
import {NavLink} from 'react-router-dom';
import LoginForm from 'src/components/LoginForm';
import {login} from "../../store/actions/users";

const LoginPage: React.FC<LoginPageProps> = ({login: signIn}) => (
    <Grid textAlign="center" verticalAlign="middle" className="fill">
    <Grid.Column style={{maxWidth: 450}}>
      <Header as="h2" color="teal" textAlign="center">
        Login to your account
      </Header>
      <LoginForm login={signIn} />
      <Message>
        New to us?
          {' '}
          <NavLink exact to="/registration">Sign Up</NavLink>
      </Message>
    </Grid.Column>
  </Grid>
);

const mapDispatchToProps = {
    login
};

const connector = connect(null, mapDispatchToProps);

type LoginPageProps = ConnectedProps<typeof connector>;

export default connector(LoginPage);
