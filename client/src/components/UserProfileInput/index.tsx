import {Grid} from "semantic-ui-react";
import styles from "./styles.module.scss";
import React from "react";
import classNames from "classnames";

interface IUserProfileInputProps {
    rowName: string;
}

const UserProfileInput: React.FC<IUserProfileInputProps> = ({rowName, children}) => {

    const addClassesToChild = child => {
        const className = classNames(
            child.props.className,
            styles.gridColumn2Item
        );

        const props = {
            className
        };

        return React.cloneElement(child, props);
    }

    return (
        <Grid.Row>
            <Grid.Column className={styles.gridColumn1} textAlign="left">
                <div>
                    {rowName}
                </div>
            </Grid.Column>
            <Grid.Column className={styles.gridColumn2}>
                {React.Children.map(children, child => addClassesToChild(child))}
            </Grid.Column>
        </Grid.Row>
    )
}

export default UserProfileInput