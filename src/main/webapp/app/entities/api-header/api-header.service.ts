import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IApiHeader } from 'app/shared/model/api-header.model';

type EntityResponseType = HttpResponse<IApiHeader>;
type EntityArrayResponseType = HttpResponse<IApiHeader[]>;

@Injectable({ providedIn: 'root' })
export class ApiHeaderService {
    public resourceUrl = SERVER_API_URL + 'api/api-headers';

    constructor(protected http: HttpClient) {}

    create(apiHeader: IApiHeader): Observable<EntityResponseType> {
        return this.http.post<IApiHeader>(this.resourceUrl, apiHeader, { observe: 'response' });
    }

    update(apiHeader: IApiHeader): Observable<EntityResponseType> {
        return this.http.put<IApiHeader>(this.resourceUrl, apiHeader, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IApiHeader>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IApiHeader[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
