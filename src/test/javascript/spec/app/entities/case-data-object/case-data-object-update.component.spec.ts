/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { CaseServiceTestModule } from '../../../test.module';
import { CaseDataObjectUpdateComponent } from 'app/entities/case-data-object/case-data-object-update.component';
import { CaseDataObjectService } from 'app/entities/case-data-object/case-data-object.service';
import { CaseDataObject } from 'app/shared/model/case-data-object.model';

describe('Component Tests', () => {
    describe('CaseDataObject Management Update Component', () => {
        let comp: CaseDataObjectUpdateComponent;
        let fixture: ComponentFixture<CaseDataObjectUpdateComponent>;
        let service: CaseDataObjectService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CaseServiceTestModule],
                declarations: [CaseDataObjectUpdateComponent]
            })
                .overrideTemplate(CaseDataObjectUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(CaseDataObjectUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CaseDataObjectService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new CaseDataObject(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.caseDataObject = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new CaseDataObject();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.caseDataObject = entity;
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
