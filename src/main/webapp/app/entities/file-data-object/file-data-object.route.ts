import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { FileDataObject } from 'app/shared/model/file-data-object.model';
import { FileDataObjectService } from './file-data-object.service';
import { FileDataObjectComponent } from './file-data-object.component';
import { FileDataObjectDetailComponent } from './file-data-object-detail.component';
import { FileDataObjectUpdateComponent } from './file-data-object-update.component';
import { FileDataObjectDeletePopupComponent } from './file-data-object-delete-dialog.component';
import { IFileDataObject } from 'app/shared/model/file-data-object.model';

@Injectable({ providedIn: 'root' })
export class FileDataObjectResolve implements Resolve<IFileDataObject> {
    constructor(private service: FileDataObjectService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IFileDataObject> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<FileDataObject>) => response.ok),
                map((fileDataObject: HttpResponse<FileDataObject>) => fileDataObject.body)
            );
        }
        return of(new FileDataObject());
    }
}

export const fileDataObjectRoute: Routes = [
    {
        path: '',
        component: FileDataObjectComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'FileDataObjects'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: FileDataObjectDetailComponent,
        resolve: {
            fileDataObject: FileDataObjectResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'FileDataObjects'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: FileDataObjectUpdateComponent,
        resolve: {
            fileDataObject: FileDataObjectResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'FileDataObjects'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: FileDataObjectUpdateComponent,
        resolve: {
            fileDataObject: FileDataObjectResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'FileDataObjects'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const fileDataObjectPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: FileDataObjectDeletePopupComponent,
        resolve: {
            fileDataObject: FileDataObjectResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'FileDataObjects'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
