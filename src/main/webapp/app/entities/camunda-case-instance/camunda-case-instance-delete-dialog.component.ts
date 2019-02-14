import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICamundaCaseInstance } from 'app/shared/model/camunda-case-instance.model';
import { CamundaCaseInstanceService } from './camunda-case-instance.service';

@Component({
    selector: 'jhi-camunda-case-instance-delete-dialog',
    templateUrl: './camunda-case-instance-delete-dialog.component.html'
})
export class CamundaCaseInstanceDeleteDialogComponent {
    camundaCaseInstance: ICamundaCaseInstance;

    constructor(
        protected camundaCaseInstanceService: CamundaCaseInstanceService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.camundaCaseInstanceService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'camundaCaseInstanceListModification',
                content: 'Deleted an camundaCaseInstance'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-camunda-case-instance-delete-popup',
    template: ''
})
export class CamundaCaseInstanceDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ camundaCaseInstance }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(CamundaCaseInstanceDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.camundaCaseInstance = camundaCaseInstance;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/camunda-case-instance', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/camunda-case-instance', { outlets: { popup: null } }]);
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
