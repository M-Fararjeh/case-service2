/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { CaseServiceTestModule } from '../../../test.module';
import { CamundaCaseInstanceDeleteDialogComponent } from 'app/entities/camunda-case-instance/camunda-case-instance-delete-dialog.component';
import { CamundaCaseInstanceService } from 'app/entities/camunda-case-instance/camunda-case-instance.service';

describe('Component Tests', () => {
    describe('CamundaCaseInstance Management Delete Component', () => {
        let comp: CamundaCaseInstanceDeleteDialogComponent;
        let fixture: ComponentFixture<CamundaCaseInstanceDeleteDialogComponent>;
        let service: CamundaCaseInstanceService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CaseServiceTestModule],
                declarations: [CamundaCaseInstanceDeleteDialogComponent]
            })
                .overrideTemplate(CamundaCaseInstanceDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(CamundaCaseInstanceDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CamundaCaseInstanceService);
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
