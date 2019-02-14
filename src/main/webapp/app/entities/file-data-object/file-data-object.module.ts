import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CaseServiceSharedModule } from 'app/shared';
import {
    FileDataObjectComponent,
    FileDataObjectDetailComponent,
    FileDataObjectUpdateComponent,
    FileDataObjectDeletePopupComponent,
    FileDataObjectDeleteDialogComponent,
    fileDataObjectRoute,
    fileDataObjectPopupRoute
} from './';

const ENTITY_STATES = [...fileDataObjectRoute, ...fileDataObjectPopupRoute];

@NgModule({
    imports: [CaseServiceSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        FileDataObjectComponent,
        FileDataObjectDetailComponent,
        FileDataObjectUpdateComponent,
        FileDataObjectDeleteDialogComponent,
        FileDataObjectDeletePopupComponent
    ],
    entryComponents: [
        FileDataObjectComponent,
        FileDataObjectUpdateComponent,
        FileDataObjectDeleteDialogComponent,
        FileDataObjectDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CaseServiceFileDataObjectModule {}
