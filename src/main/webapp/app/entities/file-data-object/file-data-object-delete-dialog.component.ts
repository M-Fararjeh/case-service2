import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IFileDataObject } from 'app/shared/model/file-data-object.model';
import { FileDataObjectService } from './file-data-object.service';

@Component({
    selector: 'jhi-file-data-object-delete-dialog',
    templateUrl: './file-data-object-delete-dialog.component.html'
})
export class FileDataObjectDeleteDialogComponent {
    fileDataObject: IFileDataObject;

    constructor(
        protected fileDataObjectService: FileDataObjectService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.fileDataObjectService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'fileDataObjectListModification',
                content: 'Deleted an fileDataObject'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-file-data-object-delete-popup',
    template: ''
})
export class FileDataObjectDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ fileDataObject }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(FileDataObjectDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.fileDataObject = fileDataObject;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/file-data-object', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/file-data-object', { outlets: { popup: null } }]);
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
