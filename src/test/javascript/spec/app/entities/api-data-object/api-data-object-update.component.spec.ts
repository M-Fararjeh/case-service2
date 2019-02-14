/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { CaseServiceTestModule } from '../../../test.module';
import { ApiDataObjectUpdateComponent } from 'app/entities/api-data-object/api-data-object-update.component';
import { ApiDataObjectService } from 'app/entities/api-data-object/api-data-object.service';
import { ApiDataObject } from 'app/shared/model/api-data-object.model';

describe('Component Tests', () => {
    describe('ApiDataObject Management Update Component', () => {
        let comp: ApiDataObjectUpdateComponent;
        let fixture: ComponentFixture<ApiDataObjectUpdateComponent>;
        let service: ApiDataObjectService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CaseServiceTestModule],
                declarations: [ApiDataObjectUpdateComponent]
            })
                .overrideTemplate(ApiDataObjectUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ApiDataObjectUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ApiDataObjectService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new ApiDataObject(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.apiDataObject = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new ApiDataObject();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.apiDataObject = entity;
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
