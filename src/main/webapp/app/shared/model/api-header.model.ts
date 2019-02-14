export interface IApiHeader {
    id?: number;
    key?: string;
    value?: string;
    apiDataObjectId?: number;
}

export class ApiHeader implements IApiHeader {
    constructor(public id?: number, public key?: string, public value?: string, public apiDataObjectId?: number) {}
}
