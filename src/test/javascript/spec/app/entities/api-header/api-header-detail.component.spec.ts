/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CaseServiceTestModule } from '../../../test.module';
import { ApiHeaderDetailComponent } from 'app/entities/api-header/api-header-detail.component';
import { ApiHeader } from 'app/shared/model/api-header.model';

describe('Component Tests', () => {
    describe('ApiHeader Management Detail Component', () => {
        let comp: ApiHeaderDetailComponent;
        let fixture: ComponentFixture<ApiHeaderDetailComponent>;
        const route = ({ data: of({ apiHeader: new ApiHeader(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CaseServiceTestModule],
                declarations: [ApiHeaderDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ApiHeaderDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ApiHeaderDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.apiHeader).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
