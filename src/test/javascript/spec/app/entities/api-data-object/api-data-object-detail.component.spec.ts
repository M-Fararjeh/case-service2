/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CaseServiceTestModule } from '../../../test.module';
import { ApiDataObjectDetailComponent } from 'app/entities/api-data-object/api-data-object-detail.component';
import { ApiDataObject } from 'app/shared/model/api-data-object.model';

describe('Component Tests', () => {
    describe('ApiDataObject Management Detail Component', () => {
        let comp: ApiDataObjectDetailComponent;
        let fixture: ComponentFixture<ApiDataObjectDetailComponent>;
        const route = ({ data: of({ apiDataObject: new ApiDataObject(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CaseServiceTestModule],
                declarations: [ApiDataObjectDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ApiDataObjectDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ApiDataObjectDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.apiDataObject).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
