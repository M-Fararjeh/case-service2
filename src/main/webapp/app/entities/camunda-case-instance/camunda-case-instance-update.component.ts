import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { ICamundaCaseInstance } from 'app/shared/model/camunda-case-instance.model';
import { CamundaCaseInstanceService } from './camunda-case-instance.service';
import { ICazeInstance } from 'app/shared/model/caze-instance.model';
import { CazeInstanceService } from 'app/entities/caze-instance';

@Component({
    selector: 'jhi-camunda-case-instance-update',
    templateUrl: './camunda-case-instance-update.component.html'
})
export class CamundaCaseInstanceUpdateComponent implements OnInit {
    camundaCaseInstance: ICamundaCaseInstance;
    isSaving: boolean;

    cazeinstances: ICazeInstance[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected camundaCaseInstanceService: CamundaCaseInstanceService,
        protected cazeInstanceService: CazeInstanceService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ camundaCaseInstance }) => {
            this.camundaCaseInstance = camundaCaseInstance;
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
        if (this.camundaCaseInstance.id !== undefined) {
            this.subscribeToSaveResponse(this.camundaCaseInstanceService.update(this.camundaCaseInstance));
        } else {
            this.subscribeToSaveResponse(this.camundaCaseInstanceService.create(this.camundaCaseInstance));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ICamundaCaseInstance>>) {
        result.subscribe((res: HttpResponse<ICamundaCaseInstance>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
