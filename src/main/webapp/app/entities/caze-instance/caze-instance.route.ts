import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { CazeInstance } from 'app/shared/model/caze-instance.model';
import { CazeInstanceService } from './caze-instance.service';
import { CazeInstanceComponent } from './caze-instance.component';
import { CazeInstanceDetailComponent } from './caze-instance-detail.component';
import { CazeInstanceUpdateComponent } from './caze-instance-update.component';
import { CazeInstanceDeletePopupComponent } from './caze-instance-delete-dialog.component';
import { ICazeInstance } from 'app/shared/model/caze-instance.model';

@Injectable({ providedIn: 'root' })
export class CazeInstanceResolve implements Resolve<ICazeInstance> {
    constructor(private service: CazeInstanceService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ICazeInstance> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<CazeInstance>) => response.ok),
                map((cazeInstance: HttpResponse<CazeInstance>) => cazeInstance.body)
            );
        }
        return of(new CazeInstance());
    }
}

export const cazeInstanceRoute: Routes = [
    {
        path: '',
        component: CazeInstanceComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'CazeInstances'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: CazeInstanceDetailComponent,
        resolve: {
            cazeInstance: CazeInstanceResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CazeInstances'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: CazeInstanceUpdateComponent,
        resolve: {
            cazeInstance: CazeInstanceResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CazeInstances'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: CazeInstanceUpdateComponent,
        resolve: {
            cazeInstance: CazeInstanceResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CazeInstances'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const cazeInstancePopupRoute: Routes = [
    {
        path: ':id/delete',
        component: CazeInstanceDeletePopupComponent,
        resolve: {
            cazeInstance: CazeInstanceResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CazeInstances'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
