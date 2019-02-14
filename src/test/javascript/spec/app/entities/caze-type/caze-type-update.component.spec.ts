/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { CaseServiceTestModule } from '../../../test.module';
import { CazeTypeUpdateComponent } from 'app/entities/caze-type/caze-type-update.component';
import { CazeTypeService } from 'app/entities/caze-type/caze-type.service';
import { CazeType } from 'app/shared/model/caze-type.model';

describe('Component Tests', () => {
    describe('CazeType Management Update Component', () => {
        let comp: CazeTypeUpdateComponent;
        let fixture: ComponentFixture<CazeTypeUpdateComponent>;
        let service: CazeTypeService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CaseServiceTestModule],
                declarations: [CazeTypeUpdateComponent]
            })
                .overrideTemplate(CazeTypeUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(CazeTypeUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CazeTypeService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new CazeType(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.cazeType = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new CazeType();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.cazeType = entity;
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
