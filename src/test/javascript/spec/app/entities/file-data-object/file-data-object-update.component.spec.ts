/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { CaseServiceTestModule } from '../../../test.module';
import { FileDataObjectUpdateComponent } from 'app/entities/file-data-object/file-data-object-update.component';
import { FileDataObjectService } from 'app/entities/file-data-object/file-data-object.service';
import { FileDataObject } from 'app/shared/model/file-data-object.model';

describe('Component Tests', () => {
    describe('FileDataObject Management Update Component', () => {
        let comp: FileDataObjectUpdateComponent;
        let fixture: ComponentFixture<FileDataObjectUpdateComponent>;
        let service: FileDataObjectService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CaseServiceTestModule],
                declarations: [FileDataObjectUpdateComponent]
            })
                .overrideTemplate(FileDataObjectUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(FileDataObjectUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(FileDataObjectService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new FileDataObject(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.fileDataObject = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new FileDataObject();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.fileDataObject = entity;
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
