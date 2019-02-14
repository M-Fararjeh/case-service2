/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { CaseServiceTestModule } from '../../../test.module';
import { DbDataObjectUpdateComponent } from 'app/entities/db-data-object/db-data-object-update.component';
import { DbDataObjectService } from 'app/entities/db-data-object/db-data-object.service';
import { DbDataObject } from 'app/shared/model/db-data-object.model';

describe('Component Tests', () => {
    describe('DbDataObject Management Update Component', () => {
        let comp: DbDataObjectUpdateComponent;
        let fixture: ComponentFixture<DbDataObjectUpdateComponent>;
        let service: DbDataObjectService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CaseServiceTestModule],
                declarations: [DbDataObjectUpdateComponent]
            })
                .overrideTemplate(DbDataObjectUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(DbDataObjectUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DbDataObjectService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new DbDataObject(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.dbDataObject = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new DbDataObject();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.dbDataObject = entity;
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
