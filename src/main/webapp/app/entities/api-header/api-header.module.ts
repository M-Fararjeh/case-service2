import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CaseServiceSharedModule } from 'app/shared';
import {
    ApiHeaderComponent,
    ApiHeaderDetailComponent,
    ApiHeaderUpdateComponent,
    ApiHeaderDeletePopupComponent,
    ApiHeaderDeleteDialogComponent,
    apiHeaderRoute,
    apiHeaderPopupRoute
} from './';

const ENTITY_STATES = [...apiHeaderRoute, ...apiHeaderPopupRoute];

@NgModule({
    imports: [CaseServiceSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ApiHeaderComponent,
        ApiHeaderDetailComponent,
        ApiHeaderUpdateComponent,
        ApiHeaderDeleteDialogComponent,
        ApiHeaderDeletePopupComponent
    ],
    entryComponents: [ApiHeaderComponent, ApiHeaderUpdateComponent, ApiHeaderDeleteDialogComponent, ApiHeaderDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CaseServiceApiHeaderModule {}
