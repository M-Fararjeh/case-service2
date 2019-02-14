import { ICazeInstance } from 'app/shared/model/caze-instance.model';

export interface ICamundaProcessInstance {
    id?: number;
    processInstanceId?: string;
    processInstanceName?: string;
    cazeInstances?: ICazeInstance[];
}

export class CamundaProcessInstance implements ICamundaProcessInstance {
    constructor(
        public id?: number,
        public processInstanceId?: string,
        public processInstanceName?: string,
        public cazeInstances?: ICazeInstance[]
    ) {}
}
