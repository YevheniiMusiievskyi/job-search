const regexExp = /^[0-9a-fA-F]{8}\b-[0-9a-fA-F]{4}\b-[0-9a-fA-F]{4}\b-[0-9a-fA-F]{4}\b-[0-9a-fA-F]{12}$/gi;

export function isUUID(value: string): boolean {
    return regexExp.test(value);
}

export function trimToSize(str: string, size: number): string {
    const sizeToSpace = regexIndexOf(str, /\s/, size)
    return str.substr(0, Math.max(size, sizeToSpace))
}

function regexIndexOf(string, regex, startpos) {
    const indexOf = string.substring(startpos || 0).search(regex);
    return (indexOf >= 0) ? (indexOf + (startpos || 0)) : indexOf;
}