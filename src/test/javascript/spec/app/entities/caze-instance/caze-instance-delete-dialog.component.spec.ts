/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { CaseServiceTestModule } from '../../../test.module';
import { CazeInstanceDeleteDialogComponent } from 'app/entities/caze-instance/caze-instance-delete-dialog.component';
import { CazeInstanceService } from 'app/entities/caze-instance/caze-instance.service';

describe('Component Tests', () => {
    describe('CazeInstance Management Delete Component', () => {
        let comp: CazeInstanceDeleteDialogComponent;
        let fixture: ComponentFixture<CazeInstanceDeleteDialogComponent>;
        let service: CazeInstanceService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CaseServiceTestModule],
                declarations: [CazeInstanceDeleteDialogComponent]
            })
                .overrideTemplate(CazeInstanceDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(CazeInstanceDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CazeInstanceService);
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
