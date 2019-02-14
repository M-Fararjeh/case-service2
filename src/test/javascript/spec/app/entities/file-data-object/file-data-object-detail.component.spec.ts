/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CaseServiceTestModule } from '../../../test.module';
import { FileDataObjectDetailComponent } from 'app/entities/file-data-object/file-data-object-detail.component';
import { FileDataObject } from 'app/shared/model/file-data-object.model';

describe('Component Tests', () => {
    describe('FileDataObject Management Detail Component', () => {
        let comp: FileDataObjectDetailComponent;
        let fixture: ComponentFixture<FileDataObjectDetailComponent>;
        const route = ({ data: of({ fileDataObject: new FileDataObject(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CaseServiceTestModule],
                declarations: [FileDataObjectDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(FileDataObjectDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(FileDataObjectDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.fileDataObject).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
