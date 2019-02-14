import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CaseServiceSharedModule } from 'app/shared';
import {
    CazeInstanceComponent,
    CazeInstanceDetailComponent,
    CazeInstanceUpdateComponent,
    CazeInstanceDeletePopupComponent,
    CazeInstanceDeleteDialogComponent,
    cazeInstanceRoute,
    cazeInstancePopupRoute
} from './';

const ENTITY_STATES = [...cazeInstanceRoute, ...cazeInstancePopupRoute];

@NgModule({
    imports: [CaseServiceSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        CazeInstanceComponent,
        CazeInstanceDetailComponent,
        CazeInstanceUpdateComponent,
        CazeInstanceDeleteDialogComponent,
        CazeInstanceDeletePopupComponent
    ],
    entryComponents: [
        CazeInstanceComponent,
        CazeInstanceUpdateComponent,
        CazeInstanceDeleteDialogComponent,
        CazeInstanceDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CaseServiceCazeInstanceModule {}
