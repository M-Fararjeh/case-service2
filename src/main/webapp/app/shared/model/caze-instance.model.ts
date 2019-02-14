import { Moment } from 'moment';
import { ICaseDataObject } from 'app/shared/model/case-data-object.model';
import { ICamundaCaseInstance } from 'app/shared/model/camunda-case-instance.model';
import { ICamundaProcessInstance } from 'app/shared/model/camunda-process-instance.model';
import { ICazeInstance } from 'app/shared/model/caze-instance.model';

export const enum CasePriority {
    HIGH = 'HIGH',
    NORMAL = 'NORMAL',
    LOW = 'LOW'
}

export interface ICazeInstance {
    id?: number;
    name?: string;
    description?: string;
    number?: string;
    creatorId?: string;
    issuerID?: string;
    creationDate?: Moment;
    caseDate?: Moment;
    priority?: CasePriority;
    requiredTime?: number;
    secured?: boolean;
    cmmnId?: string;
    requestId?: number;
    caseDataObjects?: ICaseDataObject[];
    cazeTypeId?: number;
    camundaCaseInstances?: ICamundaCaseInstance[];
    camundaProcessInstances?: ICamundaProcessInstance[];
    relatedCazes?: ICazeInstance[];
}

export class CazeInstance implements ICazeInstance {
    constructor(
        public id?: number,
        public name?: string,
        public description?: string,
        public number?: string,
        public creatorId?: string,
        public issuerID?: string,
        public creationDate?: Moment,
        public caseDate?: Moment,
        public priority?: CasePriority,
        public requiredTime?: number,
        public secured?: boolean,
        public cmmnId?: string,
        public requestId?: number,
        public caseDataObjects?: ICaseDataObject[],
        public cazeTypeId?: number,
        public camundaCaseInstances?: ICamundaCaseInstance[],
        public camundaProcessInstances?: ICamundaProcessInstance[],
        public relatedCazes?: ICazeInstance[]
    ) {
        this.secured = this.secured || false;
    }
}
