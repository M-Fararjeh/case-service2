import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICamundaProcessInstance } from 'app/shared/model/camunda-process-instance.model';

@Component({
    selector: 'jhi-camunda-process-instance-detail',
    templateUrl: './camunda-process-instance-detail.component.html'
})
export class CamundaProcessInstanceDetailComponent implements OnInit {
    camundaProcessInstance: ICamundaProcessInstance;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ camundaProcessInstance }) => {
            this.camundaProcessInstance = camundaProcessInstance;
        });
    }

    previousState() {
        window.history.back();
    }
}
