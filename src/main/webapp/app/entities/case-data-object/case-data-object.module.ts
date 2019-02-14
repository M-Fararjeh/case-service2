import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CaseServiceSharedModule } from 'app/shared';
import {
    CaseDataObjectComponent,
    CaseDataObjectDetailComponent,
    CaseDataObjectUpdateComponent,
    CaseDataObjectDeletePopupComponent,
    CaseDataObjectDeleteDialogComponent,
    caseDataObjectRoute,
    caseDataObjectPopupRoute
} from './';

const ENTITY_STATES = [...caseDataObjectRoute, ...caseDataObjectPopupRoute];

@NgModule({
    imports: [CaseServiceSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        CaseDataObjectComponent,
        CaseDataObjectDetailComponent,
        CaseDataObjectUpdateComponent,
        CaseDataObjectDeleteDialogComponent,
        CaseDataObjectDeletePopupComponent
    ],
    entryComponents: [
        CaseDataObjectComponent,
        CaseDataObjectUpdateComponent,
        CaseDataObjectDeleteDialogComponent,
        CaseDataObjectDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CaseServiceCaseDataObjectModule {}
