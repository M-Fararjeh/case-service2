import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IDbDataObject } from 'app/shared/model/db-data-object.model';

type EntityResponseType = HttpResponse<IDbDataObject>;
type EntityArrayResponseType = HttpResponse<IDbDataObject[]>;

@Injectable({ providedIn: 'root' })
export class DbDataObjectService {
    public resourceUrl = SERVER_API_URL + 'api/db-data-objects';

    constructor(protected http: HttpClient) {}

    create(dbDataObject: IDbDataObject): Observable<EntityResponseType> {
        return this.http.post<IDbDataObject>(this.resourceUrl, dbDataObject, { observe: 'response' });
    }

    update(dbDataObject: IDbDataObject): Observable<EntityResponseType> {
        return this.http.put<IDbDataObject>(this.resourceUrl, dbDataObject, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IDbDataObject>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IDbDataObject[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
