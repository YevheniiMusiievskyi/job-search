import React from "react";
import {ISkill} from "../../models/skills";
import styles from "./styles.module.scss";
import {Grid} from "semantic-ui-react";

interface SkillsViewProps {
    skills: ISkill[] | undefined
}

const SkillsView: React.FC<SkillsViewProps> = ({ skills }) => {
    return (
        <Grid>
            {skills?.map(skill => {
                return <span className={styles.skill}>
                            <b>{skill.name}</b>
                        </span>
            })}
        </Grid>
    )
}

export default SkillsView