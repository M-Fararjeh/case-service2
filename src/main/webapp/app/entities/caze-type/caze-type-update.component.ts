import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { ICazeType } from 'app/shared/model/caze-type.model';
import { CazeTypeService } from './caze-type.service';
import { ICategory } from 'app/shared/model/category.model';
import { CategoryService } from 'app/entities/category';

@Component({
    selector: 'jhi-caze-type-update',
    templateUrl: './caze-type-update.component.html'
})
export class CazeTypeUpdateComponent implements OnInit {
    cazeType: ICazeType;
    isSaving: boolean;

    categories: ICategory[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected cazeTypeService: CazeTypeService,
        protected categoryService: CategoryService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ cazeType }) => {
            this.cazeType = cazeType;
        });
        this.categoryService
            .query({ 'cazeTypeId.specified': 'false' })
            .pipe(
                filter((mayBeOk: HttpResponse<ICategory[]>) => mayBeOk.ok),
                map((response: HttpResponse<ICategory[]>) => response.body)
            )
            .subscribe(
                (res: ICategory[]) => {
                    if (!this.cazeType.categoryId) {
                        this.categories = res;
                    } else {
                        this.categoryService
                            .find(this.cazeType.categoryId)
                            .pipe(
                                filter((subResMayBeOk: HttpResponse<ICategory>) => subResMayBeOk.ok),
                                map((subResponse: HttpResponse<ICategory>) => subResponse.body)
                            )
                            .subscribe(
                                (subRes: ICategory) => (this.categories = [subRes].concat(res)),
                                (subRes: HttpErrorResponse) => this.onError(subRes.message)
                            );
                    }
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.cazeType.id !== undefined) {
            this.subscribeToSaveResponse(this.cazeTypeService.update(this.cazeType));
        } else {
            this.subscribeToSaveResponse(this.cazeTypeService.create(this.cazeType));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ICazeType>>) {
        result.subscribe((res: HttpResponse<ICazeType>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackCategoryById(index: number, item: ICategory) {
        return item.id;
    }
}
