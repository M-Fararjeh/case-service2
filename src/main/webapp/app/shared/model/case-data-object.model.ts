export const enum DataObjectType {
    API = 'API',
    DB = 'DB',
    FILE = 'FILE'
}

export interface ICaseDataObject {
    id?: number;
    type?: DataObjectType;
    key?: string;
    apiDataObjectId?: number;
    dbDataObjectId?: number;
    fileDataObjectId?: number;
    cazeInstanceId?: number;
}

export class CaseDataObject implements ICaseDataObject {
    constructor(
        public id?: number,
        public type?: DataObjectType,
        public key?: string,
        public apiDataObjectId?: number,
        public dbDataObjectId?: number,
        public fileDataObjectId?: number,
        public cazeInstanceId?: number
    ) {}
}
