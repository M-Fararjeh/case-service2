import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CaseServiceSharedModule } from 'app/shared';
import {
    CamundaCaseInstanceComponent,
    CamundaCaseInstanceDetailComponent,
    CamundaCaseInstanceUpdateComponent,
    CamundaCaseInstanceDeletePopupComponent,
    CamundaCaseInstanceDeleteDialogComponent,
    camundaCaseInstanceRoute,
    camundaCaseInstancePopupRoute
} from './';

const ENTITY_STATES = [...camundaCaseInstanceRoute, ...camundaCaseInstancePopupRoute];

@NgModule({
    imports: [CaseServiceSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        CamundaCaseInstanceComponent,
        CamundaCaseInstanceDetailComponent,
        CamundaCaseInstanceUpdateComponent,
        CamundaCaseInstanceDeleteDialogComponent,
        CamundaCaseInstanceDeletePopupComponent
    ],
    entryComponents: [
        CamundaCaseInstanceComponent,
        CamundaCaseInstanceUpdateComponent,
        CamundaCaseInstanceDeleteDialogComponent,
        CamundaCaseInstanceDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CaseServiceCamundaCaseInstanceModule {}
