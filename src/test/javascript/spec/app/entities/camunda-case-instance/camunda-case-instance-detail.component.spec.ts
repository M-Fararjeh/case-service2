/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CaseServiceTestModule } from '../../../test.module';
import { CamundaCaseInstanceDetailComponent } from 'app/entities/camunda-case-instance/camunda-case-instance-detail.component';
import { CamundaCaseInstance } from 'app/shared/model/camunda-case-instance.model';

describe('Component Tests', () => {
    describe('CamundaCaseInstance Management Detail Component', () => {
        let comp: CamundaCaseInstanceDetailComponent;
        let fixture: ComponentFixture<CamundaCaseInstanceDetailComponent>;
        const route = ({ data: of({ camundaCaseInstance: new CamundaCaseInstance(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CaseServiceTestModule],
                declarations: [CamundaCaseInstanceDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(CamundaCaseInstanceDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(CamundaCaseInstanceDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.camundaCaseInstance).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
