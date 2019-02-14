import { ICaseDataObject } from 'app/shared/model/case-data-object.model';

export interface IDbDataObject {
    id?: number;
    tableName?: string;
    columnName?: string;
    columnValue?: string;
    caseDataObjects?: ICaseDataObject[];
}

export class DbDataObject implements IDbDataObject {
    constructor(
        public id?: number,
        public tableName?: string,
        public columnName?: string,
        public columnValue?: string,
        public caseDataObjects?: ICaseDataObject[]
    ) {}
}
