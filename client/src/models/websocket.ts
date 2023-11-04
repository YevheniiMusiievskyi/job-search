export interface Topic {
    destination: string;
    callback(param?: any): void;
}