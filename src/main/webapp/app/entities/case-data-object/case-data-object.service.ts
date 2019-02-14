import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ICaseDataObject } from 'app/shared/model/case-data-object.model';

type EntityResponseType = HttpResponse<ICaseDataObject>;
type EntityArrayResponseType = HttpResponse<ICaseDataObject[]>;

@Injectable({ providedIn: 'root' })
export class CaseDataObjectService {
    public resourceUrl = SERVER_API_URL + 'api/case-data-objects';

    constructor(protected http: HttpClient) {}

    create(caseDataObject: ICaseDataObject): Observable<EntityResponseType> {
        return this.http.post<ICaseDataObject>(this.resourceUrl, caseDataObject, { observe: 'response' });
    }

    update(caseDataObject: ICaseDataObject): Observable<EntityResponseType> {
        return this.http.put<ICaseDataObject>(this.resourceUrl, caseDataObject, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ICaseDataObject>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ICaseDataObject[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
