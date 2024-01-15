export interface IUser {
    id: string;
    fullName: string;
    email?: string;
    avatar?: string;
}

export interface IRegistrationRequest {
    email: string;
    fullName: string;
    password: string;
    role: string;
}

export interface IAuthUser {
    token: string;
    user: IUser;
}

export interface IToken {
    authorities: string[]
    sub: string
}