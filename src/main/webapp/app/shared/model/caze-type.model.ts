export const enum CasePriority {
    HIGH = 'HIGH',
    NORMAL = 'NORMAL',
    LOW = 'LOW'
}

export interface ICazeType {
    id?: number;
    name?: string;
    priority?: CasePriority;
    requiredTime?: number;
    secured?: boolean;
    categoryId?: number;
}

export class CazeType implements ICazeType {
    constructor(
        public id?: number,
        public name?: string,
        public priority?: CasePriority,
        public requiredTime?: number,
        public secured?: boolean,
        public categoryId?: number
    ) {
        this.secured = this.secured || false;
    }
}
