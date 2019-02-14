import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { CamundaCaseInstance } from 'app/shared/model/camunda-case-instance.model';
import { CamundaCaseInstanceService } from './camunda-case-instance.service';
import { CamundaCaseInstanceComponent } from './camunda-case-instance.component';
import { CamundaCaseInstanceDetailComponent } from './camunda-case-instance-detail.component';
import { CamundaCaseInstanceUpdateComponent } from './camunda-case-instance-update.component';
import { CamundaCaseInstanceDeletePopupComponent } from './camunda-case-instance-delete-dialog.component';
import { ICamundaCaseInstance } from 'app/shared/model/camunda-case-instance.model';

@Injectable({ providedIn: 'root' })
export class CamundaCaseInstanceResolve implements Resolve<ICamundaCaseInstance> {
    constructor(private service: CamundaCaseInstanceService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ICamundaCaseInstance> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<CamundaCaseInstance>) => response.ok),
                map((camundaCaseInstance: HttpResponse<CamundaCaseInstance>) => camundaCaseInstance.body)
            );
        }
        return of(new CamundaCaseInstance());
    }
}

export const camundaCaseInstanceRoute: Routes = [
    {
        path: '',
        component: CamundaCaseInstanceComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'CamundaCaseInstances'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: CamundaCaseInstanceDetailComponent,
        resolve: {
            camundaCaseInstance: CamundaCaseInstanceResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CamundaCaseInstances'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: CamundaCaseInstanceUpdateComponent,
        resolve: {
            camundaCaseInstance: CamundaCaseInstanceResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CamundaCaseInstances'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: CamundaCaseInstanceUpdateComponent,
        resolve: {
            camundaCaseInstance: CamundaCaseInstanceResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CamundaCaseInstances'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const camundaCaseInstancePopupRoute: Routes = [
    {
        path: ':id/delete',
        component: CamundaCaseInstanceDeletePopupComponent,
        resolve: {
            camundaCaseInstance: CamundaCaseInstanceResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CamundaCaseInstances'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
