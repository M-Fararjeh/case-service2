import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { CamundaProcessInstance } from 'app/shared/model/camunda-process-instance.model';
import { CamundaProcessInstanceService } from './camunda-process-instance.service';
import { CamundaProcessInstanceComponent } from './camunda-process-instance.component';
import { CamundaProcessInstanceDetailComponent } from './camunda-process-instance-detail.component';
import { CamundaProcessInstanceUpdateComponent } from './camunda-process-instance-update.component';
import { CamundaProcessInstanceDeletePopupComponent } from './camunda-process-instance-delete-dialog.component';
import { ICamundaProcessInstance } from 'app/shared/model/camunda-process-instance.model';

@Injectable({ providedIn: 'root' })
export class CamundaProcessInstanceResolve implements Resolve<ICamundaProcessInstance> {
    constructor(private service: CamundaProcessInstanceService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ICamundaProcessInstance> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<CamundaProcessInstance>) => response.ok),
                map((camundaProcessInstance: HttpResponse<CamundaProcessInstance>) => camundaProcessInstance.body)
            );
        }
        return of(new CamundaProcessInstance());
    }
}

export const camundaProcessInstanceRoute: Routes = [
    {
        path: '',
        component: CamundaProcessInstanceComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'CamundaProcessInstances'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: CamundaProcessInstanceDetailComponent,
        resolve: {
            camundaProcessInstance: CamundaProcessInstanceResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CamundaProcessInstances'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: CamundaProcessInstanceUpdateComponent,
        resolve: {
            camundaProcessInstance: CamundaProcessInstanceResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CamundaProcessInstances'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: CamundaProcessInstanceUpdateComponent,
        resolve: {
            camundaProcessInstance: CamundaProcessInstanceResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CamundaProcessInstances'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const camundaProcessInstancePopupRoute: Routes = [
    {
        path: ':id/delete',
        component: CamundaProcessInstanceDeletePopupComponent,
        resolve: {
            camundaProcessInstance: CamundaProcessInstanceResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CamundaProcessInstances'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
