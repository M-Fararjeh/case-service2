import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CaseServiceSharedModule } from 'app/shared';
import {
    CazeTypeComponent,
    CazeTypeDetailComponent,
    CazeTypeUpdateComponent,
    CazeTypeDeletePopupComponent,
    CazeTypeDeleteDialogComponent,
    cazeTypeRoute,
    cazeTypePopupRoute
} from './';

const ENTITY_STATES = [...cazeTypeRoute, ...cazeTypePopupRoute];

@NgModule({
    imports: [CaseServiceSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        CazeTypeComponent,
        CazeTypeDetailComponent,
        CazeTypeUpdateComponent,
        CazeTypeDeleteDialogComponent,
        CazeTypeDeletePopupComponent
    ],
    entryComponents: [CazeTypeComponent, CazeTypeUpdateComponent, CazeTypeDeleteDialogComponent, CazeTypeDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CaseServiceCazeTypeModule {}
