import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICazeType } from 'app/shared/model/caze-type.model';

@Component({
    selector: 'jhi-caze-type-detail',
    templateUrl: './caze-type-detail.component.html'
})
export class CazeTypeDetailComponent implements OnInit {
    cazeType: ICazeType;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ cazeType }) => {
            this.cazeType = cazeType;
        });
    }

    previousState() {
        window.history.back();
    }
}
