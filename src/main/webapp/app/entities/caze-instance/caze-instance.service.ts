import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ICazeInstance } from 'app/shared/model/caze-instance.model';

type EntityResponseType = HttpResponse<ICazeInstance>;
type EntityArrayResponseType = HttpResponse<ICazeInstance[]>;

@Injectable({ providedIn: 'root' })
export class CazeInstanceService {
    public resourceUrl = SERVER_API_URL + 'api/caze-instances';

    constructor(protected http: HttpClient) {}

    create(cazeInstance: ICazeInstance): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(cazeInstance);
        return this.http
            .post<ICazeInstance>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(cazeInstance: ICazeInstance): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(cazeInstance);
        return this.http
            .put<ICazeInstance>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ICazeInstance>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ICazeInstance[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(cazeInstance: ICazeInstance): ICazeInstance {
        const copy: ICazeInstance = Object.assign({}, cazeInstance, {
            creationDate:
                cazeInstance.creationDate != null && cazeInstance.creationDate.isValid()
                    ? cazeInstance.creationDate.format(DATE_FORMAT)
                    : null,
            caseDate: cazeInstance.caseDate != null && cazeInstance.caseDate.isValid() ? cazeInstance.caseDate.format(DATE_FORMAT) : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.creationDate = res.body.creationDate != null ? moment(res.body.creationDate) : null;
            res.body.caseDate = res.body.caseDate != null ? moment(res.body.caseDate) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((cazeInstance: ICazeInstance) => {
                cazeInstance.creationDate = cazeInstance.creationDate != null ? moment(cazeInstance.creationDate) : null;
                cazeInstance.caseDate = cazeInstance.caseDate != null ? moment(cazeInstance.caseDate) : null;
            });
        }
        return res;
    }
}
