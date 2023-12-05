import React, {useEffect, useState} from 'react';
import {NavLink, useHistory, useLocation} from 'react-router-dom';
import {getUserImgLink} from 'src/helpers/imageHelper';
import {Header as HeaderUI, Image, Grid, Icon, Button, Menu} from 'semantic-ui-react';

import styles from './styles.module.scss';
import {IUser} from "src/models/auth";
import {links} from "../../helpers/links";

interface IHeaderProps {
    user: IUser | null;

    logout(): void;
}

enum MenuItem {
    HOME = "Home", VACANCIES = "Vacancies", CANDIDATES = "Candidates"
}

const menuMap = new Map<MenuItem, string>([
    [MenuItem.HOME, links.home],
    [MenuItem.VACANCIES, links.vacancies],
    [MenuItem.CANDIDATES, links.candidates]
])

const Header: React.FC<IHeaderProps> = ({user, logout}) => {

    const history = useHistory();
    const handleLogout = () => {
        logout();
        history.push(links.login);
    }

    const [activeMenu, setActiveMenu] = useState<MenuItem | null>(MenuItem.HOME)

    const location = useLocation();
    useEffect(() => {
        if (!new Set(menuMap.values()).has(location.pathname) && location.pathname !== links.login) {
            setActiveMenu(null)
        }
    }, [location])

    const goTo = (item: MenuItem) => {
        const link = menuMap.get(item);
        if (link !== undefined) {
            history.push(link)
            setActiveMenu(item)
        }
    }

    return (
        <div className={styles.headerWrp}>
            <Grid centered container columns="3">
                <Grid.Column>
                    {user && (
                        <NavLink exact to={links.home}>
                            <HeaderUI>
                                <Image circular src={getUserImgLink(user.avatar)}/>
                                {' '}
                                {user.username}
                            </HeaderUI>
                        </NavLink>
                    )}
                </Grid.Column>
                <Grid.Column>
                    <Menu secondary>
                        <Menu.Item
                            name='Home'
                            active={activeMenu === MenuItem.HOME}
                            onClick={() => goTo(MenuItem.HOME)}
                        />
                        <Menu.Item
                            name='Vacancies'
                            active={activeMenu === MenuItem.VACANCIES}
                            onClick={() => goTo(MenuItem.VACANCIES)}
                        />
                        <Menu.Item
                            name='Candidates'
                            active={activeMenu === MenuItem.CANDIDATES}
                            onClick={() => goTo(MenuItem.CANDIDATES)}
                        />
                    </Menu>
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
