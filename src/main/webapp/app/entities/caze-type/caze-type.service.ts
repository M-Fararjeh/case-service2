import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ICazeType } from 'app/shared/model/caze-type.model';

type EntityResponseType = HttpResponse<ICazeType>;
type EntityArrayResponseType = HttpResponse<ICazeType[]>;

@Injectable({ providedIn: 'root' })
export class CazeTypeService {
    public resourceUrl = SERVER_API_URL + 'api/caze-types';

    constructor(protected http: HttpClient) {}

    create(cazeType: ICazeType): Observable<EntityResponseType> {
        return this.http.post<ICazeType>(this.resourceUrl, cazeType, { observe: 'response' });
    }

    update(cazeType: ICazeType): Observable<EntityResponseType> {
        return this.http.put<ICazeType>(this.resourceUrl, cazeType, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ICazeType>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ICazeType[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
