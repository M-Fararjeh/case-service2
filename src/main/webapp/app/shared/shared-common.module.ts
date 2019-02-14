import { NgModule } from '@angular/core';

import { CaseServiceSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent } from './';

@NgModule({
    imports: [CaseServiceSharedLibsModule],
    declarations: [JhiAlertComponent, JhiAlertErrorComponent],
    exports: [CaseServiceSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent]
})
export class CaseServiceSharedCommonModule {}
