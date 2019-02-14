import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { NgbDateAdapter } from '@ng-bootstrap/ng-bootstrap';

import { NgbDateMomentAdapter } from './util/datepicker-adapter';
import { CaseServiceSharedLibsModule, CaseServiceSharedCommonModule, HasAnyAuthorityDirective } from './';

@NgModule({
    imports: [CaseServiceSharedLibsModule, CaseServiceSharedCommonModule],
    declarations: [HasAnyAuthorityDirective],
    providers: [{ provide: NgbDateAdapter, useClass: NgbDateMomentAdapter }],
    exports: [CaseServiceSharedCommonModule, HasAnyAuthorityDirective],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CaseServiceSharedModule {
    static forRoot() {
        return {
            ngModule: CaseServiceSharedModule
        };
    }
}
