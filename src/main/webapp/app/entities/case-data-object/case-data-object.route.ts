import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { CaseDataObject } from 'app/shared/model/case-data-object.model';
import { CaseDataObjectService } from './case-data-object.service';
import { CaseDataObjectComponent } from './case-data-object.component';
import { CaseDataObjectDetailComponent } from './case-data-object-detail.component';
import { CaseDataObjectUpdateComponent } from './case-data-object-update.component';
import { CaseDataObjectDeletePopupComponent } from './case-data-object-delete-dialog.component';
import { ICaseDataObject } from 'app/shared/model/case-data-object.model';

@Injectable({ providedIn: 'root' })
export class CaseDataObjectResolve implements Resolve<ICaseDataObject> {
    constructor(private service: CaseDataObjectService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ICaseDataObject> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<CaseDataObject>) => response.ok),
                map((caseDataObject: HttpResponse<CaseDataObject>) => caseDataObject.body)
            );
        }
        return of(new CaseDataObject());
    }
}

export const caseDataObjectRoute: Routes = [
    {
        path: '',
        component: CaseDataObjectComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'CaseDataObjects'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: CaseDataObjectDetailComponent,
        resolve: {
            caseDataObject: CaseDataObjectResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CaseDataObjects'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: CaseDataObjectUpdateComponent,
        resolve: {
            caseDataObject: CaseDataObjectResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CaseDataObjects'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: CaseDataObjectUpdateComponent,
        resolve: {
            caseDataObject: CaseDataObjectResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CaseDataObjects'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const caseDataObjectPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: CaseDataObjectDeletePopupComponent,
        resolve: {
            caseDataObject: CaseDataObjectResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CaseDataObjects'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
