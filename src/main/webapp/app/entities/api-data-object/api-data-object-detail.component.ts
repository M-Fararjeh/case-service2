import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IApiDataObject } from 'app/shared/model/api-data-object.model';

@Component({
    selector: 'jhi-api-data-object-detail',
    templateUrl: './api-data-object-detail.component.html'
})
export class ApiDataObjectDetailComponent implements OnInit {
    apiDataObject: IApiDataObject;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ apiDataObject }) => {
            this.apiDataObject = apiDataObject;
        });
    }

    previousState() {
        window.history.back();
    }
}
