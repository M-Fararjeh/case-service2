import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ApiHeader } from 'app/shared/model/api-header.model';
import { ApiHeaderService } from './api-header.service';
import { ApiHeaderComponent } from './api-header.component';
import { ApiHeaderDetailComponent } from './api-header-detail.component';
import { ApiHeaderUpdateComponent } from './api-header-update.component';
import { ApiHeaderDeletePopupComponent } from './api-header-delete-dialog.component';
import { IApiHeader } from 'app/shared/model/api-header.model';

@Injectable({ providedIn: 'root' })
export class ApiHeaderResolve implements Resolve<IApiHeader> {
    constructor(private service: ApiHeaderService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IApiHeader> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<ApiHeader>) => response.ok),
                map((apiHeader: HttpResponse<ApiHeader>) => apiHeader.body)
            );
        }
        return of(new ApiHeader());
    }
}

export const apiHeaderRoute: Routes = [
    {
        path: '',
        component: ApiHeaderComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'ApiHeaders'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: ApiHeaderDetailComponent,
        resolve: {
            apiHeader: ApiHeaderResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ApiHeaders'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: ApiHeaderUpdateComponent,
        resolve: {
            apiHeader: ApiHeaderResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ApiHeaders'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: ApiHeaderUpdateComponent,
        resolve: {
            apiHeader: ApiHeaderResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ApiHeaders'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const apiHeaderPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: ApiHeaderDeletePopupComponent,
        resolve: {
            apiHeader: ApiHeaderResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ApiHeaders'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
