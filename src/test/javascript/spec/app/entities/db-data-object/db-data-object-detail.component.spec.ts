/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CaseServiceTestModule } from '../../../test.module';
import { DbDataObjectDetailComponent } from 'app/entities/db-data-object/db-data-object-detail.component';
import { DbDataObject } from 'app/shared/model/db-data-object.model';

describe('Component Tests', () => {
    describe('DbDataObject Management Detail Component', () => {
        let comp: DbDataObjectDetailComponent;
        let fixture: ComponentFixture<DbDataObjectDetailComponent>;
        const route = ({ data: of({ dbDataObject: new DbDataObject(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CaseServiceTestModule],
                declarations: [DbDataObjectDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(DbDataObjectDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(DbDataObjectDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.dbDataObject).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
