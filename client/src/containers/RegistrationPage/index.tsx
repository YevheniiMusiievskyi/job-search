import React from 'react';
import {connect, ConnectedProps} from 'react-redux';
import {Grid, Header, Message} from 'semantic-ui-react';
import {NavLink} from 'react-router-dom';
import RegistrationForm from 'src/components/RegistrationForm';
import {register} from "../../store/actions/users";

const RegistrationPage: React.FC<RegistrationPageProps> = ({register: signOn}) => (
    <Grid textAlign="center" verticalAlign="middle" className="fill">
    <Grid.Column style={{maxWidth: 450}}>
      <Header as="h2" color="teal" textAlign="center">
        Registration
      </Header>
      <RegistrationForm register={signOn} />
      <Message>
        Alredy with us?
          {' '}
          <NavLink exact to="/login">Sign In</NavLink>
      </Message>
    </Grid.Column>
  </Grid>
);

const mapDispatchToProps = {
    register
};

const connector = connect(
    null,
    mapDispatchToProps
);

type RegistrationPageProps = ConnectedProps<typeof connector>;

export default connector(RegistrationPage);
