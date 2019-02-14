import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDbDataObject } from 'app/shared/model/db-data-object.model';

@Component({
    selector: 'jhi-db-data-object-detail',
    templateUrl: './db-data-object-detail.component.html'
})
export class DbDataObjectDetailComponent implements OnInit {
    dbDataObject: IDbDataObject;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ dbDataObject }) => {
            this.dbDataObject = dbDataObject;
        });
    }

    previousState() {
        window.history.back();
    }
}
