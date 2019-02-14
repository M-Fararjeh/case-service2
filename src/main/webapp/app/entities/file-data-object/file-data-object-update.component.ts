import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { IFileDataObject } from 'app/shared/model/file-data-object.model';
import { FileDataObjectService } from './file-data-object.service';

@Component({
    selector: 'jhi-file-data-object-update',
    templateUrl: './file-data-object-update.component.html'
})
export class FileDataObjectUpdateComponent implements OnInit {
    fileDataObject: IFileDataObject;
    isSaving: boolean;

    constructor(protected fileDataObjectService: FileDataObjectService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ fileDataObject }) => {
            this.fileDataObject = fileDataObject;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.fileDataObject.id !== undefined) {
            this.subscribeToSaveResponse(this.fileDataObjectService.update(this.fileDataObject));
        } else {
            this.subscribeToSaveResponse(this.fileDataObjectService.create(this.fileDataObject));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IFileDataObject>>) {
        result.subscribe((res: HttpResponse<IFileDataObject>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
