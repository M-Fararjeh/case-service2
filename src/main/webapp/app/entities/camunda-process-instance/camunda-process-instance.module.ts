import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CaseServiceSharedModule } from 'app/shared';
import {
    CamundaProcessInstanceComponent,
    CamundaProcessInstanceDetailComponent,
    CamundaProcessInstanceUpdateComponent,
    CamundaProcessInstanceDeletePopupComponent,
    CamundaProcessInstanceDeleteDialogComponent,
    camundaProcessInstanceRoute,
    camundaProcessInstancePopupRoute
} from './';

const ENTITY_STATES = [...camundaProcessInstanceRoute, ...camundaProcessInstancePopupRoute];

@NgModule({
    imports: [CaseServiceSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        CamundaProcessInstanceComponent,
        CamundaProcessInstanceDetailComponent,
        CamundaProcessInstanceUpdateComponent,
        CamundaProcessInstanceDeleteDialogComponent,
        CamundaProcessInstanceDeletePopupComponent
    ],
    entryComponents: [
        CamundaProcessInstanceComponent,
        CamundaProcessInstanceUpdateComponent,
        CamundaProcessInstanceDeleteDialogComponent,
        CamundaProcessInstanceDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CaseServiceCamundaProcessInstanceModule {}
