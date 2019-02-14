import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IApiDataObject } from 'app/shared/model/api-data-object.model';
import { ApiDataObjectService } from './api-data-object.service';

@Component({
    selector: 'jhi-api-data-object-delete-dialog',
    templateUrl: './api-data-object-delete-dialog.component.html'
})
export class ApiDataObjectDeleteDialogComponent {
    apiDataObject: IApiDataObject;

    constructor(
        protected apiDataObjectService: ApiDataObjectService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.apiDataObjectService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'apiDataObjectListModification',
                content: 'Deleted an apiDataObject'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-api-data-object-delete-popup',
    template: ''
})
export class ApiDataObjectDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ apiDataObject }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ApiDataObjectDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.apiDataObject = apiDataObject;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/api-data-object', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/api-data-object', { outlets: { popup: null } }]);
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
