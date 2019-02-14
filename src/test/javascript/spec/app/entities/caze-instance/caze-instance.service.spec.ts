/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { CazeInstanceService } from 'app/entities/caze-instance/caze-instance.service';
import { ICazeInstance, CazeInstance, CasePriority } from 'app/shared/model/caze-instance.model';

describe('Service Tests', () => {
    describe('CazeInstance Service', () => {
        let injector: TestBed;
        let service: CazeInstanceService;
        let httpMock: HttpTestingController;
        let elemDefault: ICazeInstance;
        let currentDate: moment.Moment;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(CazeInstanceService);
            httpMock = injector.get(HttpTestingController);
            currentDate = moment();

            elemDefault = new CazeInstance(
                0,
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                currentDate,
                currentDate,
                CasePriority.HIGH,
                0,
                false,
                'AAAAAAA'
            );
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign(
                    {
                        creationDate: currentDate.format(DATE_FORMAT),
                        caseDate: currentDate.format(DATE_FORMAT)
                    },
                    elemDefault
                );
                service
                    .find(123)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: elemDefault }));

                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should create a CazeInstance', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0,
                        creationDate: currentDate.format(DATE_FORMAT),
                        caseDate: currentDate.format(DATE_FORMAT)
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        creationDate: currentDate,
                        caseDate: currentDate
                    },
                    returnedFromService
                );
                service
                    .create(new CazeInstance(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a CazeInstance', async () => {
                const returnedFromService = Object.assign(
                    {
                        name: 'BBBBBB',
                        description: 'BBBBBB',
                        number: 'BBBBBB',
                        creatorId: 'BBBBBB',
                        issuerID: 'BBBBBB',
                        creationDate: currentDate.format(DATE_FORMAT),
                        caseDate: currentDate.format(DATE_FORMAT),
                        priority: 'BBBBBB',
                        requiredTime: 1,
                        secured: true,
                        cmmnId: 'BBBBBB'
                    },
                    elemDefault
                );

                const expected = Object.assign(
                    {
                        creationDate: currentDate,
                        caseDate: currentDate
                    },
                    returnedFromService
                );
                service
                    .update(expected)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'PUT' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should return a list of CazeInstance', async () => {
                const returnedFromService = Object.assign(
                    {
                        name: 'BBBBBB',
                        description: 'BBBBBB',
                        number: 'BBBBBB',
                        creatorId: 'BBBBBB',
                        issuerID: 'BBBBBB',
                        creationDate: currentDate.format(DATE_FORMAT),
                        caseDate: currentDate.format(DATE_FORMAT),
                        priority: 'BBBBBB',
                        requiredTime: 1,
                        secured: true,
                        cmmnId: 'BBBBBB'
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        creationDate: currentDate,
                        caseDate: currentDate
                    },
                    returnedFromService
                );
                service
                    .query(expected)
                    .pipe(
                        take(1),
                        map(resp => resp.body)
                    )
                    .subscribe(body => expect(body).toContainEqual(expected));
                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify([returnedFromService]));
                httpMock.verify();
            });

            it('should delete a CazeInstance', async () => {
                const rxPromise = service.delete(123).subscribe(resp => expect(resp.ok));

                const req = httpMock.expectOne({ method: 'DELETE' });
                req.flush({ status: 200 });
            });
        });

        afterEach(() => {
            httpMock.verify();
        });
    });
});
