import {userIdPath, vacancyIdPath} from "./links";

const regexExp = /^[0-9a-f]{8}-[0-9a-f]{4}-[0-5][0-9a-f]{3}-[089ab][0-9a-f]{3}-[0-9a-f]{12}$/i;

export function isUUID(value: string): boolean {
    return regexExp.test(value);
}

export function trimToSize(str: string | null | undefined, size: number): string {
    if (!str) {
        return ''
    }

    const sizeToSpace = regexIndexOf(str, /\s/, size)
    return str.substr(0, Math.max(size, sizeToSpace))
}

function regexIndexOf(string, regex, startpos) {
    const indexOf = string.substring(startpos || 0).search(regex);
    return (indexOf >= 0) ? (indexOf + (startpos || 0)) : indexOf;
}

export function getOrEmpty(str: string | null | undefined): string {
    return str ? str : ''
}

export function withUserId(link: string, userId: string | undefined) {
    return replacePathVariable(link, userId, userIdPath)
}

export function withVacancyId(link: string, vacancyId: string | undefined) {
    return replacePathVariable(link, vacancyId, vacancyIdPath)
}

function replacePathVariable(link: string, id: string | undefined, pathToReplace: string) {
    if (!id) {
        return link
    }

    return link.replace(pathToReplace, id)
}
