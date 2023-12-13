export interface IUser {
    id: string;
    username: string;
    email?: string;
    avatar?: string;
}

export interface IRegistrationRequest {
    username: string;
    email: string;
    password: string;
}

export interface IAuthUser {
    token: string;
    user: IUser;
}

export interface IToken {
    authorities: string[]
    sub: string
}