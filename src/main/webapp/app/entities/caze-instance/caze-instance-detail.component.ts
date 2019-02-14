import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICazeInstance } from 'app/shared/model/caze-instance.model';

@Component({
    selector: 'jhi-caze-instance-detail',
    templateUrl: './caze-instance-detail.component.html'
})
export class CazeInstanceDetailComponent implements OnInit {
    cazeInstance: ICazeInstance;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ cazeInstance }) => {
            this.cazeInstance = cazeInstance;
        });
    }

    previousState() {
        window.history.back();
    }
}
