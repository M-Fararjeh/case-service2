import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IFileDataObject } from 'app/shared/model/file-data-object.model';

@Component({
    selector: 'jhi-file-data-object-detail',
    templateUrl: './file-data-object-detail.component.html'
})
export class FileDataObjectDetailComponent implements OnInit {
    fileDataObject: IFileDataObject;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ fileDataObject }) => {
            this.fileDataObject = fileDataObject;
        });
    }

    previousState() {
        window.history.back();
    }
}
