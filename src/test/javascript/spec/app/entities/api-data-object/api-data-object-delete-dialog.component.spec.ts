/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { CaseServiceTestModule } from '../../../test.module';
import { ApiDataObjectDeleteDialogComponent } from 'app/entities/api-data-object/api-data-object-delete-dialog.component';
import { ApiDataObjectService } from 'app/entities/api-data-object/api-data-object.service';

describe('Component Tests', () => {
    describe('ApiDataObject Management Delete Component', () => {
        let comp: ApiDataObjectDeleteDialogComponent;
        let fixture: ComponentFixture<ApiDataObjectDeleteDialogComponent>;
        let service: ApiDataObjectService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CaseServiceTestModule],
                declarations: [ApiDataObjectDeleteDialogComponent]
            })
                .overrideTemplate(ApiDataObjectDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ApiDataObjectDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ApiDataObjectService);
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
