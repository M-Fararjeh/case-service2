import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { CazeType } from 'app/shared/model/caze-type.model';
import { CazeTypeService } from './caze-type.service';
import { CazeTypeComponent } from './caze-type.component';
import { CazeTypeDetailComponent } from './caze-type-detail.component';
import { CazeTypeUpdateComponent } from './caze-type-update.component';
import { CazeTypeDeletePopupComponent } from './caze-type-delete-dialog.component';
import { ICazeType } from 'app/shared/model/caze-type.model';

@Injectable({ providedIn: 'root' })
export class CazeTypeResolve implements Resolve<ICazeType> {
    constructor(private service: CazeTypeService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ICazeType> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<CazeType>) => response.ok),
                map((cazeType: HttpResponse<CazeType>) => cazeType.body)
            );
        }
        return of(new CazeType());
    }
}

export const cazeTypeRoute: Routes = [
    {
        path: '',
        component: CazeTypeComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'CazeTypes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: CazeTypeDetailComponent,
        resolve: {
            cazeType: CazeTypeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CazeTypes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: CazeTypeUpdateComponent,
        resolve: {
            cazeType: CazeTypeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CazeTypes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: CazeTypeUpdateComponent,
        resolve: {
            cazeType: CazeTypeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CazeTypes'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const cazeTypePopupRoute: Routes = [
    {
        path: ':id/delete',
        component: CazeTypeDeletePopupComponent,
        resolve: {
            cazeType: CazeTypeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CazeTypes'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
