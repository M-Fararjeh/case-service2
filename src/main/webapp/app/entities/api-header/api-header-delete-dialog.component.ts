import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IApiHeader } from 'app/shared/model/api-header.model';
import { ApiHeaderService } from './api-header.service';

@Component({
    selector: 'jhi-api-header-delete-dialog',
    templateUrl: './api-header-delete-dialog.component.html'
})
export class ApiHeaderDeleteDialogComponent {
    apiHeader: IApiHeader;

    constructor(
        protected apiHeaderService: ApiHeaderService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.apiHeaderService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'apiHeaderListModification',
                content: 'Deleted an apiHeader'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-api-header-delete-popup',
    template: ''
})
export class ApiHeaderDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ apiHeader }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ApiHeaderDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.apiHeader = apiHeader;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/api-header', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/api-header', { outlets: { popup: null } }]);
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
