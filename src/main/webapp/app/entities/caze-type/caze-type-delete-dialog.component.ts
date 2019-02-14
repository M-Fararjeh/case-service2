import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICazeType } from 'app/shared/model/caze-type.model';
import { CazeTypeService } from './caze-type.service';

@Component({
    selector: 'jhi-caze-type-delete-dialog',
    templateUrl: './caze-type-delete-dialog.component.html'
})
export class CazeTypeDeleteDialogComponent {
    cazeType: ICazeType;

    constructor(protected cazeTypeService: CazeTypeService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.cazeTypeService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'cazeTypeListModification',
                content: 'Deleted an cazeType'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-caze-type-delete-popup',
    template: ''
})
export class CazeTypeDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ cazeType }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(CazeTypeDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.cazeType = cazeType;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/caze-type', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/caze-type', { outlets: { popup: null } }]);
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
