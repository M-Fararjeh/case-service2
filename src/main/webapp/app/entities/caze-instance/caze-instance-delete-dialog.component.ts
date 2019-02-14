import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICazeInstance } from 'app/shared/model/caze-instance.model';
import { CazeInstanceService } from './caze-instance.service';

@Component({
    selector: 'jhi-caze-instance-delete-dialog',
    templateUrl: './caze-instance-delete-dialog.component.html'
})
export class CazeInstanceDeleteDialogComponent {
    cazeInstance: ICazeInstance;

    constructor(
        protected cazeInstanceService: CazeInstanceService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.cazeInstanceService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'cazeInstanceListModification',
                content: 'Deleted an cazeInstance'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-caze-instance-delete-popup',
    template: ''
})
export class CazeInstanceDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ cazeInstance }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(CazeInstanceDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.cazeInstance = cazeInstance;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/caze-instance', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/caze-instance', { outlets: { popup: null } }]);
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
