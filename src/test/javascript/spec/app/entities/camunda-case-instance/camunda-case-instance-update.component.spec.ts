/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { CaseServiceTestModule } from '../../../test.module';
import { CamundaCaseInstanceUpdateComponent } from 'app/entities/camunda-case-instance/camunda-case-instance-update.component';
import { CamundaCaseInstanceService } from 'app/entities/camunda-case-instance/camunda-case-instance.service';
import { CamundaCaseInstance } from 'app/shared/model/camunda-case-instance.model';

describe('Component Tests', () => {
    describe('CamundaCaseInstance Management Update Component', () => {
        let comp: CamundaCaseInstanceUpdateComponent;
        let fixture: ComponentFixture<CamundaCaseInstanceUpdateComponent>;
        let service: CamundaCaseInstanceService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CaseServiceTestModule],
                declarations: [CamundaCaseInstanceUpdateComponent]
            })
                .overrideTemplate(CamundaCaseInstanceUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(CamundaCaseInstanceUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CamundaCaseInstanceService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new CamundaCaseInstance(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.camundaCaseInstance = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new CamundaCaseInstance();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.camundaCaseInstance = entity;
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
