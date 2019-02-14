/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { CaseServiceTestModule } from '../../../test.module';
import { CamundaProcessInstanceUpdateComponent } from 'app/entities/camunda-process-instance/camunda-process-instance-update.component';
import { CamundaProcessInstanceService } from 'app/entities/camunda-process-instance/camunda-process-instance.service';
import { CamundaProcessInstance } from 'app/shared/model/camunda-process-instance.model';

describe('Component Tests', () => {
    describe('CamundaProcessInstance Management Update Component', () => {
        let comp: CamundaProcessInstanceUpdateComponent;
        let fixture: ComponentFixture<CamundaProcessInstanceUpdateComponent>;
        let service: CamundaProcessInstanceService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CaseServiceTestModule],
                declarations: [CamundaProcessInstanceUpdateComponent]
            })
                .overrideTemplate(CamundaProcessInstanceUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(CamundaProcessInstanceUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CamundaProcessInstanceService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new CamundaProcessInstance(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.camundaProcessInstance = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new CamundaProcessInstance();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.camundaProcessInstance = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.create).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));
        });
    });
});
