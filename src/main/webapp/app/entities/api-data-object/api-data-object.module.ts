import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CaseServiceSharedModule } from 'app/shared';
import {
    ApiDataObjectComponent,
    ApiDataObjectDetailComponent,
    ApiDataObjectUpdateComponent,
    ApiDataObjectDeletePopupComponent,
    ApiDataObjectDeleteDialogComponent,
    apiDataObjectRoute,
    apiDataObjectPopupRoute
} from './';

const ENTITY_STATES = [...apiDataObjectRoute, ...apiDataObjectPopupRoute];

@NgModule({
    imports: [CaseServiceSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ApiDataObjectComponent,
        ApiDataObjectDetailComponent,
        ApiDataObjectUpdateComponent,
        ApiDataObjectDeleteDialogComponent,
        ApiDataObjectDeletePopupComponent
    ],
    entryComponents: [
        ApiDataObjectComponent,
        ApiDataObjectUpdateComponent,
        ApiDataObjectDeleteDialogComponent,
        ApiDataObjectDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CaseServiceApiDataObjectModule {}
