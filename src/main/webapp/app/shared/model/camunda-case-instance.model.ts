import { ICazeInstance } from 'app/shared/model/caze-instance.model';

export interface ICamundaCaseInstance {
    id?: number;
    caseInstanceId?: string;
    caseInstanceName?: string;
    cazeInstances?: ICazeInstance[];
}

export class CamundaCaseInstance implements ICamundaCaseInstance {
    constructor(
        public id?: number,
        public caseInstanceId?: string,
        public caseInstanceName?: string,
        public cazeInstances?: ICazeInstance[]
    ) {}
}
