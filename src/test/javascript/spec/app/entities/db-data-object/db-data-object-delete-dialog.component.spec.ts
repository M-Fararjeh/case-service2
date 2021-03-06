/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { CaseServiceTestModule } from '../../../test.module';
import { DbDataObjectDeleteDialogComponent } from 'app/entities/db-data-object/db-data-object-delete-dialog.component';
import { DbDataObjectService } from 'app/entities/db-data-object/db-data-object.service';

describe('Component Tests', () => {
    describe('DbDataObject Management Delete Component', () => {
        let comp: DbDataObjectDeleteDialogComponent;
        let fixture: ComponentFixture<DbDataObjectDeleteDialogComponent>;
        let service: DbDataObjectService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CaseServiceTestModule],
                declarations: [DbDataObjectDeleteDialogComponent]
            })
                .overrideTemplate(DbDataObjectDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(DbDataObjectDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DbDataObjectService);
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
