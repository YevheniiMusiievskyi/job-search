import {Button, Form, Grid} from "semantic-ui-react";
import {createVacancy} from "../../store/actions/vacancies";
import {connect, ConnectedProps} from "react-redux";
import React, {useState} from "react";

const VacancyCreation: React.FC<VacancyCreationProps> = () => {

    const [title, setTitle] = useState('')
    const [description, setDescription] = useState('')

    const saveVacancy = () => {
        if (!title || !description) {
            return
        }

        createVacancy({
            title,
            description
        }).then(() => {
            setTitle('')
            setDescription('')
        })
    }

    return (
        <Form>
            <Form.Input label="Title" placeholder="Title" value={title}
                        onChange={e => setTitle(e.target.value)}/>
            <Form.TextArea label='Description' placeholder='Description' value={description}
                           onChange={e => setDescription(e.target.value)}/>
            <Grid>
                <Grid.Column textAlign="center">
                    <Button onClick={saveVacancy} primary>Save</Button>
                </Grid.Column>
            </Grid>
        </Form>
    )
}

const mapDispatchToProps = {}

const connector = connect(null, mapDispatchToProps);

type VacancyCreationProps = ConnectedProps<typeof connector>;

export default connector(VacancyCreation);