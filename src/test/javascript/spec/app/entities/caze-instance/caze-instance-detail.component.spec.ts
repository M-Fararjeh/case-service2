/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CaseServiceTestModule } from '../../../test.module';
import { CazeInstanceDetailComponent } from 'app/entities/caze-instance/caze-instance-detail.component';
import { CazeInstance } from 'app/shared/model/caze-instance.model';

describe('Component Tests', () => {
    describe('CazeInstance Management Detail Component', () => {
        let comp: CazeInstanceDetailComponent;
        let fixture: ComponentFixture<CazeInstanceDetailComponent>;
        const route = ({ data: of({ cazeInstance: new CazeInstance(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CaseServiceTestModule],
                declarations: [CazeInstanceDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(CazeInstanceDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(CazeInstanceDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.cazeInstance).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
