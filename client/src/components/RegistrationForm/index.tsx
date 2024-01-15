import React, {useState} from 'react';
import validator from 'validator';
import {Form, Button, Segment, Select} from 'semantic-ui-react';
import {IRegistrationRequest} from "../../models/auth";

interface IRegistrationFormProps {
    register(request: IRegistrationRequest): void;
}

const user = {key: "user", text: "User", value: "USER"}
const recruiter = {key: "recruiter", text: "Recruiter", value: "RECRUITER"}

const roles = [user, recruiter]

const RegistrationForm: React.FC<IRegistrationFormProps> = ({register: signOn}) => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [fullName, setFullName] = useState('');
    const [role, setRole] = useState(user.value)
    const [isLoading, setLoading] = useState(false);
    const [isEmailValid, setEmailValid] = useState(true);
    const [isFullNameValid, setFullNameValid] = useState(true);
    const [isPasswordValid, setPasswordValid] = useState(true);

    const emailChanged = value => {
        setEmail(value);
        setEmailValid(!!value);
    };

    const fullNameChanged = value => {
        setFullName(value);
        setFullNameValid(!!value);
    };

    const passwordChanged = value => {
        setPassword(value);
        setPasswordValid(!!value);
    };

    const changeRole = (event, data) => {
        setRole(data.value)
    }

    const register = async () => {
        const isValid = isEmailValid && isFullNameValid && isPasswordValid;
        if (!isValid || isLoading) {
            return;
        }
        setLoading(true);
        try {
            await signOn({email, fullName, password, role});
        } catch {
            setLoading(false);
        }
    };

    return (
        <Form name="registrationForm" size="large" onSubmit={register}>
            <Segment>
                <Form.Input
                    fluid
                    icon="at"
                    iconPosition="left"
                    placeholder="Email"
                    type="email"
                    error={!isEmailValid}
                    onChange={ev => emailChanged(ev.target.value)}
                    onBlur={() => setEmailValid(validator.isEmail(email))}
                />
                <Form.Input
                    fluid
                    icon="user"
                    iconPosition="left"
                    placeholder="Full name"
                    type="text"
                    error={!isFullNameValid}
                    onChange={ev => fullNameChanged(ev.target.value)}
                    onBlur={() => setFullNameValid(Boolean(fullName))}
                />
                <Form.Input
                    fluid
                    icon="lock"
                    iconPosition="left"
                    placeholder="Password"
                    type="password"
                    onChange={ev => passwordChanged(ev.target.value)}
                    error={!isPasswordValid}
                    onBlur={() => setPasswordValid(Boolean(password))}
                />
                <Form.Input>
                    <Select fluid options={roles} value={role} onChange={changeRole} defaultValue={user.value}/>
                </Form.Input>
                <Button type="submit" color="teal" fluid size="large" loading={isLoading} primary>
                    Register
                </Button>
            </Segment>
        </Form>
    );
};

export default RegistrationForm;
