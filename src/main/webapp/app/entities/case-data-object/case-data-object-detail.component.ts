import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICaseDataObject } from 'app/shared/model/case-data-object.model';

@Component({
    selector: 'jhi-case-data-object-detail',
    templateUrl: './case-data-object-detail.component.html'
})
export class CaseDataObjectDetailComponent implements OnInit {
    caseDataObject: ICaseDataObject;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ caseDataObject }) => {
            this.caseDataObject = caseDataObject;
        });
    }

    previousState() {
        window.history.back();
    }
}
