/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CaseServiceTestModule } from '../../../test.module';
import { CamundaProcessInstanceDetailComponent } from 'app/entities/camunda-process-instance/camunda-process-instance-detail.component';
import { CamundaProcessInstance } from 'app/shared/model/camunda-process-instance.model';

describe('Component Tests', () => {
    describe('CamundaProcessInstance Management Detail Component', () => {
        let comp: CamundaProcessInstanceDetailComponent;
        let fixture: ComponentFixture<CamundaProcessInstanceDetailComponent>;
        const route = ({ data: of({ camundaProcessInstance: new CamundaProcessInstance(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CaseServiceTestModule],
                declarations: [CamundaProcessInstanceDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(CamundaProcessInstanceDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(CamundaProcessInstanceDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.camundaProcessInstance).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
