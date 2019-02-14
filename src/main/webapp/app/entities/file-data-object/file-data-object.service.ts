import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IFileDataObject } from 'app/shared/model/file-data-object.model';

type EntityResponseType = HttpResponse<IFileDataObject>;
type EntityArrayResponseType = HttpResponse<IFileDataObject[]>;

@Injectable({ providedIn: 'root' })
export class FileDataObjectService {
    public resourceUrl = SERVER_API_URL + 'api/file-data-objects';

    constructor(protected http: HttpClient) {}

    create(fileDataObject: IFileDataObject): Observable<EntityResponseType> {
        return this.http.post<IFileDataObject>(this.resourceUrl, fileDataObject, { observe: 'response' });
    }

    update(fileDataObject: IFileDataObject): Observable<EntityResponseType> {
        return this.http.put<IFileDataObject>(this.resourceUrl, fileDataObject, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IFileDataObject>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IFileDataObject[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
