import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { DbDataObject } from 'app/shared/model/db-data-object.model';
import { DbDataObjectService } from './db-data-object.service';
import { DbDataObjectComponent } from './db-data-object.component';
import { DbDataObjectDetailComponent } from './db-data-object-detail.component';
import { DbDataObjectUpdateComponent } from './db-data-object-update.component';
import { DbDataObjectDeletePopupComponent } from './db-data-object-delete-dialog.component';
import { IDbDataObject } from 'app/shared/model/db-data-object.model';

@Injectable({ providedIn: 'root' })
export class DbDataObjectResolve implements Resolve<IDbDataObject> {
    constructor(private service: DbDataObjectService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IDbDataObject> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<DbDataObject>) => response.ok),
                map((dbDataObject: HttpResponse<DbDataObject>) => dbDataObject.body)
            );
        }
        return of(new DbDataObject());
    }
}

export const dbDataObjectRoute: Routes = [
    {
        path: '',
        component: DbDataObjectComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'DbDataObjects'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: DbDataObjectDetailComponent,
        resolve: {
            dbDataObject: DbDataObjectResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'DbDataObjects'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: DbDataObjectUpdateComponent,
        resolve: {
            dbDataObject: DbDataObjectResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'DbDataObjects'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: DbDataObjectUpdateComponent,
        resolve: {
            dbDataObject: DbDataObjectResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'DbDataObjects'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const dbDataObjectPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: DbDataObjectDeletePopupComponent,
        resolve: {
            dbDataObject: DbDataObjectResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'DbDataObjects'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
