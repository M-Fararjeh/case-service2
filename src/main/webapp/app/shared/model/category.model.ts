import { ICategory } from 'app/shared/model/category.model';

export interface ICategory {
    id?: number;
    key?: string;
    name?: string;
    subCategories?: ICategory[];
    cazeTypeId?: number;
    parentCategoryId?: number;
}

export class Category implements ICategory {
    constructor(
        public id?: number,
        public key?: string,
        public name?: string,
        public subCategories?: ICategory[],
        public cazeTypeId?: number,
        public parentCategoryId?: number
    ) {}
}
