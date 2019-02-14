/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CaseServiceTestModule } from '../../../test.module';
import { CaseDataObjectDetailComponent } from 'app/entities/case-data-object/case-data-object-detail.component';
import { CaseDataObject } from 'app/shared/model/case-data-object.model';

describe('Component Tests', () => {
    describe('CaseDataObject Management Detail Component', () => {
        let comp: CaseDataObjectDetailComponent;
        let fixture: ComponentFixture<CaseDataObjectDetailComponent>;
        const route = ({ data: of({ caseDataObject: new CaseDataObject(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CaseServiceTestModule],
                declarations: [CaseDataObjectDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(CaseDataObjectDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(CaseDataObjectDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.caseDataObject).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
