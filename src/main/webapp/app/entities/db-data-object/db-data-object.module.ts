import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CaseServiceSharedModule } from 'app/shared';
import {
    DbDataObjectComponent,
    DbDataObjectDetailComponent,
    DbDataObjectUpdateComponent,
    DbDataObjectDeletePopupComponent,
    DbDataObjectDeleteDialogComponent,
    dbDataObjectRoute,
    dbDataObjectPopupRoute
} from './';

const ENTITY_STATES = [...dbDataObjectRoute, ...dbDataObjectPopupRoute];

@NgModule({
    imports: [CaseServiceSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        DbDataObjectComponent,
        DbDataObjectDetailComponent,
        DbDataObjectUpdateComponent,
        DbDataObjectDeleteDialogComponent,
        DbDataObjectDeletePopupComponent
    ],
    entryComponents: [
        DbDataObjectComponent,
        DbDataObjectUpdateComponent,
        DbDataObjectDeleteDialogComponent,
        DbDataObjectDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CaseServiceDbDataObjectModule {}
