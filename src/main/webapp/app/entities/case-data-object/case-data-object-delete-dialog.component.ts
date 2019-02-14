import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICaseDataObject } from 'app/shared/model/case-data-object.model';
import { CaseDataObjectService } from './case-data-object.service';

@Component({
    selector: 'jhi-case-data-object-delete-dialog',
    templateUrl: './case-data-object-delete-dialog.component.html'
})
export class CaseDataObjectDeleteDialogComponent {
    caseDataObject: ICaseDataObject;

    constructor(
        protected caseDataObjectService: CaseDataObjectService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.caseDataObjectService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'caseDataObjectListModification',
                content: 'Deleted an caseDataObject'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-case-data-object-delete-popup',
    template: ''
})
export class CaseDataObjectDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ caseDataObject }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(CaseDataObjectDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.caseDataObject = caseDataObject;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/case-data-object', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/case-data-object', { outlets: { popup: null } }]);
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
