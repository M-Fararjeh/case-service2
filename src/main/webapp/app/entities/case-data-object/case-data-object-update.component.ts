import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { ICaseDataObject } from 'app/shared/model/case-data-object.model';
import { CaseDataObjectService } from './case-data-object.service';
import { IApiDataObject } from 'app/shared/model/api-data-object.model';
import { ApiDataObjectService } from 'app/entities/api-data-object';
import { IDbDataObject } from 'app/shared/model/db-data-object.model';
import { DbDataObjectService } from 'app/entities/db-data-object';
import { IFileDataObject } from 'app/shared/model/file-data-object.model';
import { FileDataObjectService } from 'app/entities/file-data-object';
import { ICazeInstance } from 'app/shared/model/caze-instance.model';
import { CazeInstanceService } from 'app/entities/caze-instance';

@Component({
    selector: 'jhi-case-data-object-update',
    templateUrl: './case-data-object-update.component.html'
})
export class CaseDataObjectUpdateComponent implements OnInit {
    caseDataObject: ICaseDataObject;
    isSaving: boolean;

    apidataobjects: IApiDataObject[];

    dbdataobjects: IDbDataObject[];

    filedataobjects: IFileDataObject[];

    cazeinstances: ICazeInstance[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected caseDataObjectService: CaseDataObjectService,
        protected apiDataObjectService: ApiDataObjectService,
        protected dbDataObjectService: DbDataObjectService,
        protected fileDataObjectService: FileDataObjectService,
        protected cazeInstanceService: CazeInstanceService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ caseDataObject }) => {
            this.caseDataObject = caseDataObject;
        });
        this.apiDataObjectService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IApiDataObject[]>) => mayBeOk.ok),
                map((response: HttpResponse<IApiDataObject[]>) => response.body)
            )
            .subscribe((res: IApiDataObject[]) => (this.apidataobjects = res), (res: HttpErrorResponse) => this.onError(res.message));
        this.dbDataObjectService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IDbDataObject[]>) => mayBeOk.ok),
                map((response: HttpResponse<IDbDataObject[]>) => response.body)
            )
            .subscribe((res: IDbDataObject[]) => (this.dbdataobjects = res), (res: HttpErrorResponse) => this.onError(res.message));
        this.fileDataObjectService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IFileDataObject[]>) => mayBeOk.ok),
                map((response: HttpResponse<IFileDataObject[]>) => response.body)
            )
            .subscribe((res: IFileDataObject[]) => (this.filedataobjects = res), (res: HttpErrorResponse) => this.onError(res.message));
        this.cazeInstanceService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<ICazeInstance[]>) => mayBeOk.ok),
                map((response: HttpResponse<ICazeInstance[]>) => response.body)
            )
            .subscribe((res: ICazeInstance[]) => (this.cazeinstances = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.caseDataObject.id !== undefined) {
            this.subscribeToSaveResponse(this.caseDataObjectService.update(this.caseDataObject));
        } else {
            this.subscribeToSaveResponse(this.caseDataObjectService.create(this.caseDataObject));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ICaseDataObject>>) {
        result.subscribe((res: HttpResponse<ICaseDataObject>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackDbDataObjectById(index: number, item: IDbDataObject) {
        return item.id;
    }

    trackFileDataObjectById(index: number, item: IFileDataObject) {
        return item.id;
    }

    trackCazeInstanceById(index: number, item: ICazeInstance) {
        return item.id;
    }
}
