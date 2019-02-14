import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ICamundaProcessInstance } from 'app/shared/model/camunda-process-instance.model';

type EntityResponseType = HttpResponse<ICamundaProcessInstance>;
type EntityArrayResponseType = HttpResponse<ICamundaProcessInstance[]>;

@Injectable({ providedIn: 'root' })
export class CamundaProcessInstanceService {
    public resourceUrl = SERVER_API_URL + 'api/camunda-process-instances';

    constructor(protected http: HttpClient) {}

    create(camundaProcessInstance: ICamundaProcessInstance): Observable<EntityResponseType> {
        return this.http.post<ICamundaProcessInstance>(this.resourceUrl, camundaProcessInstance, { observe: 'response' });
    }

    update(camundaProcessInstance: ICamundaProcessInstance): Observable<EntityResponseType> {
        return this.http.put<ICamundaProcessInstance>(this.resourceUrl, camundaProcessInstance, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ICamundaProcessInstance>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ICamundaProcessInstance[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
