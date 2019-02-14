import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICamundaCaseInstance } from 'app/shared/model/camunda-case-instance.model';

@Component({
    selector: 'jhi-camunda-case-instance-detail',
    templateUrl: './camunda-case-instance-detail.component.html'
})
export class CamundaCaseInstanceDetailComponent implements OnInit {
    camundaCaseInstance: ICamundaCaseInstance;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ camundaCaseInstance }) => {
            this.camundaCaseInstance = camundaCaseInstance;
        });
    }

    previousState() {
        window.history.back();
    }
}
