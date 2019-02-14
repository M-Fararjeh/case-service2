/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CaseServiceTestModule } from '../../../test.module';
import { CazeTypeDetailComponent } from 'app/entities/caze-type/caze-type-detail.component';
import { CazeType } from 'app/shared/model/caze-type.model';

describe('Component Tests', () => {
    describe('CazeType Management Detail Component', () => {
        let comp: CazeTypeDetailComponent;
        let fixture: ComponentFixture<CazeTypeDetailComponent>;
        const route = ({ data: of({ cazeType: new CazeType(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CaseServiceTestModule],
                declarations: [CazeTypeDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(CazeTypeDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(CazeTypeDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.cazeType).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
