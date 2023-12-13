import {IToken} from "../models/auth";

export function getUserId(): string {
    return parseToken().sub
}

export function isRecruiter(): boolean {
    const authorities = parseToken().authorities
    return authorities.includes("ROLE_RECRUITER")
}

function parseToken(): IToken {
    const value = `; ${document.cookie}`;
    const parts = value.split(`; Authorization=`);

    const jwt = parts.length === 2 ? parts[1].split(";").shift() : null
    if (jwt) {
        return JSON.parse(Buffer.from(jwt.split('.')[1], 'base64').toString())
    } else {
        throw new Error("Token not found")
    }
}