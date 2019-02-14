/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { CaseServiceTestModule } from '../../../test.module';
import { FileDataObjectDeleteDialogComponent } from 'app/entities/file-data-object/file-data-object-delete-dialog.component';
import { FileDataObjectService } from 'app/entities/file-data-object/file-data-object.service';

describe('Component Tests', () => {
    describe('FileDataObject Management Delete Component', () => {
        let comp: FileDataObjectDeleteDialogComponent;
        let fixture: ComponentFixture<FileDataObjectDeleteDialogComponent>;
        let service: FileDataObjectService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CaseServiceTestModule],
                declarations: [FileDataObjectDeleteDialogComponent]
            })
                .overrideTemplate(FileDataObjectDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(FileDataObjectDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(FileDataObjectService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
