import React from 'react';
import {NavLink, useHistory} from 'react-router-dom';
import {getUserImgLink} from 'src/helpers/imageHelper';
import {Header as HeaderUI, Image, Grid, Icon, Button} from 'semantic-ui-react';

import styles from './styles.module.scss';
import {IUser} from "src/models/auth";

interface IHeaderProps {
    user: IUser | null;

    logout(): void;
}

const Header: React.FC<IHeaderProps> = ({user, logout}) => {

    const history = useHistory();
    const handleLogout = () => {
        logout();
        history.push("/login");
    }

    return (
        <div className={styles.headerWrp}>
            <Grid centered container columns="2">
                <Grid.Column>
                    {user && (
                        <NavLink exact to="/">
                            <HeaderUI>
                                <Image circular src={getUserImgLink(user.avatar)}/>
                                {' '}
                                {user.username}
                            </HeaderUI>
                        </NavLink>
                    )}
                </Grid.Column>
                <Grid.Column textAlign="right">
                    <NavLink exact activeClassName="active" to={`/profile/${user?.id}`} className={styles.menuBtn}>
                        <Icon name="user circle" size="large"/>
                    </NavLink>
                    <Button basic icon type="button" className={`${styles.menuBtn} ${styles.logoutBtn}`}
                            onClick={handleLogout}>
                        <Icon name="log out" size="large"/>
                    </Button>
                </Grid.Column>
            </Grid>
        </div>
    )
};

export default Header;
