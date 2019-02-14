/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { CaseServiceTestModule } from '../../../test.module';
import { CazeInstanceUpdateComponent } from 'app/entities/caze-instance/caze-instance-update.component';
import { CazeInstanceService } from 'app/entities/caze-instance/caze-instance.service';
import { CazeInstance } from 'app/shared/model/caze-instance.model';

describe('Component Tests', () => {
    describe('CazeInstance Management Update Component', () => {
        let comp: CazeInstanceUpdateComponent;
        let fixture: ComponentFixture<CazeInstanceUpdateComponent>;
        let service: CazeInstanceService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CaseServiceTestModule],
                declarations: [CazeInstanceUpdateComponent]
            })
                .overrideTemplate(CazeInstanceUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(CazeInstanceUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CazeInstanceService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new CazeInstance(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.cazeInstance = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new CazeInstance();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.cazeInstance = entity;
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
