import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IApiHeader } from 'app/shared/model/api-header.model';

@Component({
    selector: 'jhi-api-header-detail',
    templateUrl: './api-header-detail.component.html'
})
export class ApiHeaderDetailComponent implements OnInit {
    apiHeader: IApiHeader;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ apiHeader }) => {
            this.apiHeader = apiHeader;
        });
    }

    previousState() {
        window.history.back();
    }
}
