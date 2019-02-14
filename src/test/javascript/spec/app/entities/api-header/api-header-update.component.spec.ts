/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { CaseServiceTestModule } from '../../../test.module';
import { ApiHeaderUpdateComponent } from 'app/entities/api-header/api-header-update.component';
import { ApiHeaderService } from 'app/entities/api-header/api-header.service';
import { ApiHeader } from 'app/shared/model/api-header.model';

describe('Component Tests', () => {
    describe('ApiHeader Management Update Component', () => {
        let comp: ApiHeaderUpdateComponent;
        let fixture: ComponentFixture<ApiHeaderUpdateComponent>;
        let service: ApiHeaderService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CaseServiceTestModule],
                declarations: [ApiHeaderUpdateComponent]
            })
                .overrideTemplate(ApiHeaderUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ApiHeaderUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ApiHeaderService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new ApiHeader(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.apiHeader = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new ApiHeader();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.apiHeader = entity;
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
