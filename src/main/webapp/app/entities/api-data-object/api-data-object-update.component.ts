import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { IApiDataObject } from 'app/shared/model/api-data-object.model';
import { ApiDataObjectService } from './api-data-object.service';

@Component({
    selector: 'jhi-api-data-object-update',
    templateUrl: './api-data-object-update.component.html'
})
export class ApiDataObjectUpdateComponent implements OnInit {
    apiDataObject: IApiDataObject;
    isSaving: boolean;

    constructor(protected apiDataObjectService: ApiDataObjectService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ apiDataObject }) => {
            this.apiDataObject = apiDataObject;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.apiDataObject.id !== undefined) {
            this.subscribeToSaveResponse(this.apiDataObjectService.update(this.apiDataObject));
        } else {
            this.subscribeToSaveResponse(this.apiDataObjectService.create(this.apiDataObject));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IApiDataObject>>) {
        result.subscribe((res: HttpResponse<IApiDataObject>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
