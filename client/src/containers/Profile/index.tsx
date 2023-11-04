import React from 'react';
import {connect, ConnectedProps} from 'react-redux';
import { getUserImgLink } from 'src/helpers/imageHelper';
import {
    Grid,
    Image,
    Input
} from 'semantic-ui-react';
import {IRootState} from "src/store";
import AvatarUploader from "../../components/AvatarUploader";
import {uploadAvatar} from "../../store/actions/images";

const Profile: React.FC<ProfileProps> = ({ user, uploadAvatar }) => (
    <Grid container textAlign="center" style={{ paddingTop: 30 }}>
    <Grid.Column>
      <Image centered src={getUserImgLink(user?.avatar)} size="medium" circular />
      <AvatarUploader uploadAvatar={uploadAvatar} />
      <br />
      <Input
          icon="user"
          iconPosition="left"
          placeholder="Username"
          type="text"
          disabled
          value={user?.username}
      />
      <br />
      <br />
      <Input
          icon="at"
          iconPosition="left"
          placeholder="Email"
          type="email"
          disabled
          value={user?.email}
      />
    </Grid.Column>
  </Grid>
);

const mapStateToProps = (state: IRootState) => ({
    user: state.profile.user
});

const mapDispatchToProps = {
    uploadAvatar
}

const connector = connect(mapStateToProps, mapDispatchToProps);

type ProfileProps = ConnectedProps<typeof connector>;

export default connector(Profile);
