import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IApiDataObject } from 'app/shared/model/api-data-object.model';

type EntityResponseType = HttpResponse<IApiDataObject>;
type EntityArrayResponseType = HttpResponse<IApiDataObject[]>;

@Injectable({ providedIn: 'root' })
export class ApiDataObjectService {
    public resourceUrl = SERVER_API_URL + 'api/api-data-objects';

    constructor(protected http: HttpClient) {}

    create(apiDataObject: IApiDataObject): Observable<EntityResponseType> {
        return this.http.post<IApiDataObject>(this.resourceUrl, apiDataObject, { observe: 'response' });
    }

    update(apiDataObject: IApiDataObject): Observable<EntityResponseType> {
        return this.http.put<IApiDataObject>(this.resourceUrl, apiDataObject, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IApiDataObject>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IApiDataObject[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
