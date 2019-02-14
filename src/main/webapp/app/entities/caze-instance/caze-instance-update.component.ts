import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { JhiAlertService } from 'ng-jhipster';
import { ICazeInstance } from 'app/shared/model/caze-instance.model';
import { CazeInstanceService } from './caze-instance.service';
import { ICazeType } from 'app/shared/model/caze-type.model';
import { CazeTypeService } from 'app/entities/caze-type';
import { ICamundaCaseInstance } from 'app/shared/model/camunda-case-instance.model';
import { CamundaCaseInstanceService } from 'app/entities/camunda-case-instance';
import { ICamundaProcessInstance } from 'app/shared/model/camunda-process-instance.model';
import { CamundaProcessInstanceService } from 'app/entities/camunda-process-instance';

@Component({
    selector: 'jhi-caze-instance-update',
    templateUrl: './caze-instance-update.component.html'
})
export class CazeInstanceUpdateComponent implements OnInit {
    cazeInstance: ICazeInstance;
    isSaving: boolean;

    cazetypes: ICazeType[];

    camundacaseinstances: ICamundaCaseInstance[];

    camundaprocessinstances: ICamundaProcessInstance[];

    cazeinstances: ICazeInstance[];
    creationDateDp: any;
    caseDateDp: any;

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected cazeInstanceService: CazeInstanceService,
        protected cazeTypeService: CazeTypeService,
        protected camundaCaseInstanceService: CamundaCaseInstanceService,
        protected camundaProcessInstanceService: CamundaProcessInstanceService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ cazeInstance }) => {
            this.cazeInstance = cazeInstance;
        });
        this.cazeTypeService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<ICazeType[]>) => mayBeOk.ok),
                map((response: HttpResponse<ICazeType[]>) => response.body)
            )
            .subscribe((res: ICazeType[]) => (this.cazetypes = res), (res: HttpErrorResponse) => this.onError(res.message));
        this.camundaCaseInstanceService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<ICamundaCaseInstance[]>) => mayBeOk.ok),
                map((response: HttpResponse<ICamundaCaseInstance[]>) => response.body)
            )
            .subscribe(
                (res: ICamundaCaseInstance[]) => (this.camundacaseinstances = res),
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        this.camundaProcessInstanceService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<ICamundaProcessInstance[]>) => mayBeOk.ok),
                map((response: HttpResponse<ICamundaProcessInstance[]>) => response.body)
            )
            .subscribe(
                (res: ICamundaProcessInstance[]) => (this.camundaprocessinstances = res),
                (res: HttpErrorResponse) => this.onError(res.message)
            );
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
        if (this.cazeInstance.id !== undefined) {
            this.subscribeToSaveResponse(this.cazeInstanceService.update(this.cazeInstance));
        } else {
            this.subscribeToSaveResponse(this.cazeInstanceService.create(this.cazeInstance));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ICazeInstance>>) {
        result.subscribe((res: HttpResponse<ICazeInstance>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackCazeTypeById(index: number, item: ICazeType) {
        return item.id;
    }

    trackCamundaCaseInstanceById(index: number, item: ICamundaCaseInstance) {
        return item.id;
    }

    trackCamundaProcessInstanceById(index: number, item: ICamundaProcessInstance) {
        return item.id;
    }

    trackCazeInstanceById(index: number, item: ICazeInstance) {
        return item.id;
    }

    getSelected(selectedVals: Array<any>, option: any) {
        if (selectedVals) {
            for (let i = 0; i < selectedVals.length; i++) {
                if (option.id === selectedVals[i].id) {
                    return selectedVals[i];
                }
            }
        }
        return option;
    }
}
