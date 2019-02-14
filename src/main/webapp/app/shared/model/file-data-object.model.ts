import { ICaseDataObject } from 'app/shared/model/case-data-object.model';

export interface IFileDataObject {
    id?: number;
    cmisId?: string;
    path?: string;
    caseDataObjects?: ICaseDataObject[];
}

export class FileDataObject implements IFileDataObject {
    constructor(public id?: number, public cmisId?: string, public path?: string, public caseDataObjects?: ICaseDataObject[]) {}
}
