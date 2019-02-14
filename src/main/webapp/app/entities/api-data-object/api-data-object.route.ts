import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ApiDataObject } from 'app/shared/model/api-data-object.model';
import { ApiDataObjectService } from './api-data-object.service';
import { ApiDataObjectComponent } from './api-data-object.component';
import { ApiDataObjectDetailComponent } from './api-data-object-detail.component';
import { ApiDataObjectUpdateComponent } from './api-data-object-update.component';
import { ApiDataObjectDeletePopupComponent } from './api-data-object-delete-dialog.component';
import { IApiDataObject } from 'app/shared/model/api-data-object.model';

@Injectable({ providedIn: 'root' })
export class ApiDataObjectResolve implements Resolve<IApiDataObject> {
    constructor(private service: ApiDataObjectService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IApiDataObject> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<ApiDataObject>) => response.ok),
                map((apiDataObject: HttpResponse<ApiDataObject>) => apiDataObject.body)
            );
        }
        return of(new ApiDataObject());
    }
}

export const apiDataObjectRoute: Routes = [
    {
        path: '',
        component: ApiDataObjectComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'ApiDataObjects'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: ApiDataObjectDetailComponent,
        resolve: {
            apiDataObject: ApiDataObjectResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ApiDataObjects'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: ApiDataObjectUpdateComponent,
        resolve: {
            apiDataObject: ApiDataObjectResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ApiDataObjects'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: ApiDataObjectUpdateComponent,
        resolve: {
            apiDataObject: ApiDataObjectResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ApiDataObjects'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const apiDataObjectPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: ApiDataObjectDeletePopupComponent,
        resolve: {
            apiDataObject: ApiDataObjectResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ApiDataObjects'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
