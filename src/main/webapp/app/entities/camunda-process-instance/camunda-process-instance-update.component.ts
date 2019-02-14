import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { ICamundaProcessInstance } from 'app/shared/model/camunda-process-instance.model';
import { CamundaProcessInstanceService } from './camunda-process-instance.service';
import { ICazeInstance } from 'app/shared/model/caze-instance.model';
import { CazeInstanceService } from 'app/entities/caze-instance';

@Component({
    selector: 'jhi-camunda-process-instance-update',
    templateUrl: './camunda-process-instance-update.component.html'
})
export class CamundaProcessInstanceUpdateComponent implements OnInit {
    camundaProcessInstance: ICamundaProcessInstance;
    isSaving: boolean;

    cazeinstances: ICazeInstance[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected camundaProcessInstanceService: CamundaProcessInstanceService,
        protected cazeInstanceService: CazeInstanceService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ camundaProcessInstance }) => {
            this.camundaProcessInstance = camundaProcessInstance;
        });
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
        if (this.camundaProcessInstance.id !== undefined) {
            this.subscribeToSaveResponse(this.camundaProcessInstanceService.update(this.camundaProcessInstance));
        } else {
            this.subscribeToSaveResponse(this.camundaProcessInstanceService.create(this.camundaProcessInstance));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ICamundaProcessInstance>>) {
        result.subscribe(
            (res: HttpResponse<ICamundaProcessInstance>) => this.onSaveSuccess(),
            (res: HttpErrorResponse) => this.onSaveError()
        );
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
