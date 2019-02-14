import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICamundaProcessInstance } from 'app/shared/model/camunda-process-instance.model';
import { CamundaProcessInstanceService } from './camunda-process-instance.service';

@Component({
    selector: 'jhi-camunda-process-instance-delete-dialog',
    templateUrl: './camunda-process-instance-delete-dialog.component.html'
})
export class CamundaProcessInstanceDeleteDialogComponent {
    camundaProcessInstance: ICamundaProcessInstance;

    constructor(
        protected camundaProcessInstanceService: CamundaProcessInstanceService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.camundaProcessInstanceService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'camundaProcessInstanceListModification',
                content: 'Deleted an camundaProcessInstance'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-camunda-process-instance-delete-popup',
    template: ''
})
export class CamundaProcessInstanceDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ camundaProcessInstance }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(CamundaProcessInstanceDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.camundaProcessInstance = camundaProcessInstance;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/camunda-process-instance', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/camunda-process-instance', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
