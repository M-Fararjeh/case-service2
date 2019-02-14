import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDbDataObject } from 'app/shared/model/db-data-object.model';
import { DbDataObjectService } from './db-data-object.service';

@Component({
    selector: 'jhi-db-data-object-delete-dialog',
    templateUrl: './db-data-object-delete-dialog.component.html'
})
export class DbDataObjectDeleteDialogComponent {
    dbDataObject: IDbDataObject;

    constructor(
        protected dbDataObjectService: DbDataObjectService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.dbDataObjectService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'dbDataObjectListModification',
                content: 'Deleted an dbDataObject'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-db-data-object-delete-popup',
    template: ''
})
export class DbDataObjectDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ dbDataObject }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(DbDataObjectDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.dbDataObject = dbDataObject;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/db-data-object', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/db-data-object', { outlets: { popup: null } }]);
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
