import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { IDbDataObject } from 'app/shared/model/db-data-object.model';
import { DbDataObjectService } from './db-data-object.service';

@Component({
    selector: 'jhi-db-data-object-update',
    templateUrl: './db-data-object-update.component.html'
})
export class DbDataObjectUpdateComponent implements OnInit {
    dbDataObject: IDbDataObject;
    isSaving: boolean;

    constructor(protected dbDataObjectService: DbDataObjectService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ dbDataObject }) => {
            this.dbDataObject = dbDataObject;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.dbDataObject.id !== undefined) {
            this.subscribeToSaveResponse(this.dbDataObjectService.update(this.dbDataObject));
        } else {
            this.subscribeToSaveResponse(this.dbDataObjectService.create(this.dbDataObject));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IDbDataObject>>) {
        result.subscribe((res: HttpResponse<IDbDataObject>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
