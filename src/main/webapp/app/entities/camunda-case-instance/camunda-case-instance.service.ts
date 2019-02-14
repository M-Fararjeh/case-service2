import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ICamundaCaseInstance } from 'app/shared/model/camunda-case-instance.model';

type EntityResponseType = HttpResponse<ICamundaCaseInstance>;
type EntityArrayResponseType = HttpResponse<ICamundaCaseInstance[]>;

@Injectable({ providedIn: 'root' })
export class CamundaCaseInstanceService {
    public resourceUrl = SERVER_API_URL + 'api/camunda-case-instances';

    constructor(protected http: HttpClient) {}

    create(camundaCaseInstance: ICamundaCaseInstance): Observable<EntityResponseType> {
        return this.http.post<ICamundaCaseInstance>(this.resourceUrl, camundaCaseInstance, { observe: 'response' });
    }

    update(camundaCaseInstance: ICamundaCaseInstance): Observable<EntityResponseType> {
        return this.http.put<ICamundaCaseInstance>(this.resourceUrl, camundaCaseInstance, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ICamundaCaseInstance>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ICamundaCaseInstance[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
