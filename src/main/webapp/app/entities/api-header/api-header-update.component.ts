import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IApiHeader } from 'app/shared/model/api-header.model';
import { ApiHeaderService } from './api-header.service';
import { IApiDataObject } from 'app/shared/model/api-data-object.model';
import { ApiDataObjectService } from 'app/entities/api-data-object';

@Component({
    selector: 'jhi-api-header-update',
    templateUrl: './api-header-update.component.html'
})
export class ApiHeaderUpdateComponent implements OnInit {
    apiHeader: IApiHeader;
    isSaving: boolean;

    apidataobjects: IApiDataObject[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected apiHeaderService: ApiHeaderService,
        protected apiDataObjectService: ApiDataObjectService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ apiHeader }) => {
            this.apiHeader = apiHeader;
        });
        this.apiDataObjectService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IApiDataObject[]>) => mayBeOk.ok),
                map((response: HttpResponse<IApiDataObject[]>) => response.body)
            )
            .subscribe((res: IApiDataObject[]) => (this.apidataobjects = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.apiHeader.id !== undefined) {
            this.subscribeToSaveResponse(this.apiHeaderService.update(this.apiHeader));
        } else {
            this.subscribeToSaveResponse(this.apiHeaderService.create(this.apiHeader));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IApiHeader>>) {
        result.subscribe((res: HttpResponse<IApiHeader>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackApiDataObjectById(index: number, item: IApiDataObject) {
        return item.id;
    }
}
