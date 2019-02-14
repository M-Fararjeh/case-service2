import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
    imports: [
        RouterModule.forChild([
            {
                path: 'caze-instance',
                loadChildren: './caze-instance/caze-instance.module#CaseServiceCazeInstanceModule'
            },
            {
                path: 'caze-type',
                loadChildren: './caze-type/caze-type.module#CaseServiceCazeTypeModule'
            },
            {
                path: 'camunda-case-instance',
                loadChildren: './camunda-case-instance/camunda-case-instance.module#CaseServiceCamundaCaseInstanceModule'
            },
            {
                path: 'camunda-process-instance',
                loadChildren: './camunda-process-instance/camunda-process-instance.module#CaseServiceCamundaProcessInstanceModule'
            },
            {
                path: 'case-data-object',
                loadChildren: './case-data-object/case-data-object.module#CaseServiceCaseDataObjectModule'
            },
            {
                path: 'api-data-object',
                loadChildren: './api-data-object/api-data-object.module#CaseServiceApiDataObjectModule'
            },
            {
                path: 'api-header',
                loadChildren: './api-header/api-header.module#CaseServiceApiHeaderModule'
            },
            {
                path: 'db-data-object',
                loadChildren: './db-data-object/db-data-object.module#CaseServiceDbDataObjectModule'
            },
            {
                path: 'file-data-object',
                loadChildren: './file-data-object/file-data-object.module#CaseServiceFileDataObjectModule'
            },
            {
                path: 'category',
                loadChildren: './category/category.module#CaseServiceCategoryModule'
            },
            {
                path: 'caze-instance',
                loadChildren: './caze-instance/caze-instance.module#CaseServiceCazeInstanceModule'
            },
            {
                path: 'caze-type',
                loadChildren: './caze-type/caze-type.module#CaseServiceCazeTypeModule'
            },
            {
                path: 'camunda-case-instance',
                loadChildren: './camunda-case-instance/camunda-case-instance.module#CaseServiceCamundaCaseInstanceModule'
            },
            {
                path: 'camunda-process-instance',
                loadChildren: './camunda-process-instance/camunda-process-instance.module#CaseServiceCamundaProcessInstanceModule'
            },
            {
                path: 'case-data-object',
                loadChildren: './case-data-object/case-data-object.module#CaseServiceCaseDataObjectModule'
            },
            {
                path: 'api-data-object',
                loadChildren: './api-data-object/api-data-object.module#CaseServiceApiDataObjectModule'
            },
            {
                path: 'api-header',
                loadChildren: './api-header/api-header.module#CaseServiceApiHeaderModule'
            },
            {
                path: 'db-data-object',
                loadChildren: './db-data-object/db-data-object.module#CaseServiceDbDataObjectModule'
            },
            {
                path: 'file-data-object',
                loadChildren: './file-data-object/file-data-object.module#CaseServiceFileDataObjectModule'
            },
            {
                path: 'category',
                loadChildren: './category/category.module#CaseServiceCategoryModule'
            },
            {
                path: 'caze-instance',
                loadChildren: './caze-instance/caze-instance.module#CaseServiceCazeInstanceModule'
            },
            {
                path: 'caze-type',
                loadChildren: './caze-type/caze-type.module#CaseServiceCazeTypeModule'
            },
            {
                path: 'camunda-case-instance',
                loadChildren: './camunda-case-instance/camunda-case-instance.module#CaseServiceCamundaCaseInstanceModule'
            },
            {
                path: 'camunda-process-instance',
                loadChildren: './camunda-process-instance/camunda-process-instance.module#CaseServiceCamundaProcessInstanceModule'
            },
            {
                path: 'case-data-object',
                loadChildren: './case-data-object/case-data-object.module#CaseServiceCaseDataObjectModule'
            },
            {
                path: 'api-data-object',
                loadChildren: './api-data-object/api-data-object.module#CaseServiceApiDataObjectModule'
            },
            {
                path: 'api-header',
                loadChildren: './api-header/api-header.module#CaseServiceApiHeaderModule'
            },
            {
                path: 'db-data-object',
                loadChildren: './db-data-object/db-data-object.module#CaseServiceDbDataObjectModule'
            },
            {
                path: 'file-data-object',
                loadChildren: './file-data-object/file-data-object.module#CaseServiceFileDataObjectModule'
            },
            {
                path: 'category',
                loadChildren: './category/category.module#CaseServiceCategoryModule'
            },
            {
                path: 'caze-instance',
                loadChildren: './caze-instance/caze-instance.module#CaseServiceCazeInstanceModule'
            },
            {
                path: 'caze-type',
                loadChildren: './caze-type/caze-type.module#CaseServiceCazeTypeModule'
            },
            {
                path: 'camunda-case-instance',
                loadChildren: './camunda-case-instance/camunda-case-instance.module#CaseServiceCamundaCaseInstanceModule'
            },
            {
                path: 'camunda-process-instance',
                loadChildren: './camunda-process-instance/camunda-process-instance.module#CaseServiceCamundaProcessInstanceModule'
            },
            {
                path: 'case-data-object',
                loadChildren: './case-data-object/case-data-object.module#CaseServiceCaseDataObjectModule'
            },
            {
                path: 'api-data-object',
                loadChildren: './api-data-object/api-data-object.module#CaseServiceApiDataObjectModule'
            },
            {
                path: 'api-header',
                loadChildren: './api-header/api-header.module#CaseServiceApiHeaderModule'
            },
            {
                path: 'db-data-object',
                loadChildren: './db-data-object/db-data-object.module#CaseServiceDbDataObjectModule'
            },
            {
                path: 'file-data-object',
                loadChildren: './file-data-object/file-data-object.module#CaseServiceFileDataObjectModule'
            },
            {
                path: 'category',
                loadChildren: './category/category.module#CaseServiceCategoryModule'
            },
            {
                path: 'caze-instance',
                loadChildren: './caze-instance/caze-instance.module#CaseServiceCazeInstanceModule'
            },
            {
                path: 'caze-type',
                loadChildren: './caze-type/caze-type.module#CaseServiceCazeTypeModule'
            },
            {
                path: 'camunda-case-instance',
                loadChildren: './camunda-case-instance/camunda-case-instance.module#CaseServiceCamundaCaseInstanceModule'
            },
            {
                path: 'camunda-process-instance',
                loadChildren: './camunda-process-instance/camunda-process-instance.module#CaseServiceCamundaProcessInstanceModule'
            },
            {
                path: 'case-data-object',
                loadChildren: './case-data-object/case-data-object.module#CaseServiceCaseDataObjectModule'
            },
            {
                path: 'api-data-object',
                loadChildren: './api-data-object/api-data-object.module#CaseServiceApiDataObjectModule'
            },
            {
                path: 'api-header',
                loadChildren: './api-header/api-header.module#CaseServiceApiHeaderModule'
            },
            {
                path: 'db-data-object',
                loadChildren: './db-data-object/db-data-object.module#CaseServiceDbDataObjectModule'
            },
            {
                path: 'file-data-object',
                loadChildren: './file-data-object/file-data-object.module#CaseServiceFileDataObjectModule'
            },
            {
                path: 'category',
                loadChildren: './category/category.module#CaseServiceCategoryModule'
            },
            {
                path: 'caze-instance',
                loadChildren: './caze-instance/caze-instance.module#CaseServiceCazeInstanceModule'
            },
            {
                path: 'caze-type',
                loadChildren: './caze-type/caze-type.module#CaseServiceCazeTypeModule'
            },
            {
                path: 'camunda-case-instance',
                loadChildren: './camunda-case-instance/camunda-case-instance.module#CaseServiceCamundaCaseInstanceModule'
            },
            {
                path: 'camunda-process-instance',
                loadChildren: './camunda-process-instance/camunda-process-instance.module#CaseServiceCamundaProcessInstanceModule'
            },
            {
                path: 'case-data-object',
                loadChildren: './case-data-object/case-data-object.module#CaseServiceCaseDataObjectModule'
            },
            {
                path: 'api-data-object',
                loadChildren: './api-data-object/api-data-object.module#CaseServiceApiDataObjectModule'
            },
            {
                path: 'api-header',
                loadChildren: './api-header/api-header.module#CaseServiceApiHeaderModule'
            },
            {
                path: 'db-data-object',
                loadChildren: './db-data-object/db-data-object.module#CaseServiceDbDataObjectModule'
            },
            {
                path: 'file-data-object',
                loadChildren: './file-data-object/file-data-object.module#CaseServiceFileDataObjectModule'
            },
            {
                path: 'category',
                loadChildren: './category/category.module#CaseServiceCategoryModule'
            },
            {
                path: 'caze-instance',
                loadChildren: './caze-instance/caze-instance.module#CaseServiceCazeInstanceModule'
            },
            {
                path: 'caze-type',
                loadChildren: './caze-type/caze-type.module#CaseServiceCazeTypeModule'
            },
            {
                path: 'camunda-case-instance',
                loadChildren: './camunda-case-instance/camunda-case-instance.module#CaseServiceCamundaCaseInstanceModule'
            },
            {
                path: 'camunda-process-instance',
                loadChildren: './camunda-process-instance/camunda-process-instance.module#CaseServiceCamundaProcessInstanceModule'
            },
            {
                path: 'case-data-object',
                loadChildren: './case-data-object/case-data-object.module#CaseServiceCaseDataObjectModule'
            },
            {
                path: 'api-data-object',
                loadChildren: './api-data-object/api-data-object.module#CaseServiceApiDataObjectModule'
            },
            {
                path: 'api-header',
                loadChildren: './api-header/api-header.module#CaseServiceApiHeaderModule'
            },
            {
                path: 'db-data-object',
                loadChildren: './db-data-object/db-data-object.module#CaseServiceDbDataObjectModule'
            },
            {
                path: 'file-data-object',
                loadChildren: './file-data-object/file-data-object.module#CaseServiceFileDataObjectModule'
            },
            {
                path: 'category',
                loadChildren: './category/category.module#CaseServiceCategoryModule'
            },
            {
                path: 'caze-instance',
                loadChildren: './caze-instance/caze-instance.module#CaseServiceCazeInstanceModule'
            },
            {
                path: 'caze-type',
                loadChildren: './caze-type/caze-type.module#CaseServiceCazeTypeModule'
            },
            {
                path: 'camunda-case-instance',
                loadChildren: './camunda-case-instance/camunda-case-instance.module#CaseServiceCamundaCaseInstanceModule'
            },
            {
                path: 'camunda-process-instance',
                loadChildren: './camunda-process-instance/camunda-process-instance.module#CaseServiceCamundaProcessInstanceModule'
            },
            {
                path: 'case-data-object',
                loadChildren: './case-data-object/case-data-object.module#CaseServiceCaseDataObjectModule'
            },
            {
                path: 'api-data-object',
                loadChildren: './api-data-object/api-data-object.module#CaseServiceApiDataObjectModule'
            },
            {
                path: 'api-header',
                loadChildren: './api-header/api-header.module#CaseServiceApiHeaderModule'
            },
            {
                path: 'db-data-object',
                loadChildren: './db-data-object/db-data-object.module#CaseServiceDbDataObjectModule'
            },
            {
                path: 'file-data-object',
                loadChildren: './file-data-object/file-data-object.module#CaseServiceFileDataObjectModule'
            },
            {
                path: 'category',
                loadChildren: './category/category.module#CaseServiceCategoryModule'
            },
            {
                path: 'caze-instance',
                loadChildren: './caze-instance/caze-instance.module#CaseServiceCazeInstanceModule'
            },
            {
                path: 'caze-type',
                loadChildren: './caze-type/caze-type.module#CaseServiceCazeTypeModule'
            },
            {
                path: 'camunda-case-instance',
                loadChildren: './camunda-case-instance/camunda-case-instance.module#CaseServiceCamundaCaseInstanceModule'
            },
            {
                path: 'camunda-process-instance',
                loadChildren: './camunda-process-instance/camunda-process-instance.module#CaseServiceCamundaProcessInstanceModule'
            },
            {
                path: 'case-data-object',
                loadChildren: './case-data-object/case-data-object.module#CaseServiceCaseDataObjectModule'
            },
            {
                path: 'api-data-object',
                loadChildren: './api-data-object/api-data-object.module#CaseServiceApiDataObjectModule'
            },
            {
                path: 'api-header',
                loadChildren: './api-header/api-header.module#CaseServiceApiHeaderModule'
            },
            {
                path: 'db-data-object',
                loadChildren: './db-data-object/db-data-object.module#CaseServiceDbDataObjectModule'
            },
            {
                path: 'file-data-object',
                loadChildren: './file-data-object/file-data-object.module#CaseServiceFileDataObjectModule'
            },
            {
                path: 'category',
                loadChildren: './category/category.module#CaseServiceCategoryModule'
            },
            {
                path: 'caze-instance',
                loadChildren: './caze-instance/caze-instance.module#CaseServiceCazeInstanceModule'
            },
            {
                path: 'caze-type',
                loadChildren: './caze-type/caze-type.module#CaseServiceCazeTypeModule'
            },
            {
                path: 'camunda-case-instance',
                loadChildren: './camunda-case-instance/camunda-case-instance.module#CaseServiceCamundaCaseInstanceModule'
            },
            {
                path: 'camunda-process-instance',
                loadChildren: './camunda-process-instance/camunda-process-instance.module#CaseServiceCamundaProcessInstanceModule'
            },
            {
                path: 'case-data-object',
                loadChildren: './case-data-object/case-data-object.module#CaseServiceCaseDataObjectModule'
            },
            {
                path: 'api-data-object',
                loadChildren: './api-data-object/api-data-object.module#CaseServiceApiDataObjectModule'
            },
            {
                path: 'api-header',
                loadChildren: './api-header/api-header.module#CaseServiceApiHeaderModule'
            },
            {
                path: 'db-data-object',
                loadChildren: './db-data-object/db-data-object.module#CaseServiceDbDataObjectModule'
            },
            {
                path: 'file-data-object',
                loadChildren: './file-data-object/file-data-object.module#CaseServiceFileDataObjectModule'
            },
            {
                path: 'category',
                loadChildren: './category/category.module#CaseServiceCategoryModule'
            },
            {
                path: 'caze-instance',
                loadChildren: './caze-instance/caze-instance.module#CaseServiceCazeInstanceModule'
            },
            {
                path: 'caze-type',
                loadChildren: './caze-type/caze-type.module#CaseServiceCazeTypeModule'
            },
            {
                path: 'camunda-case-instance',
                loadChildren: './camunda-case-instance/camunda-case-instance.module#CaseServiceCamundaCaseInstanceModule'
            },
            {
                path: 'camunda-process-instance',
                loadChildren: './camunda-process-instance/camunda-process-instance.module#CaseServiceCamundaProcessInstanceModule'
            },
            {
                path: 'case-data-object',
                loadChildren: './case-data-object/case-data-object.module#CaseServiceCaseDataObjectModule'
            },
            {
                path: 'api-data-object',
                loadChildren: './api-data-object/api-data-object.module#CaseServiceApiDataObjectModule'
            },
            {
                path: 'api-header',
                loadChildren: './api-header/api-header.module#CaseServiceApiHeaderModule'
            },
            {
                path: 'db-data-object',
                loadChildren: './db-data-object/db-data-object.module#CaseServiceDbDataObjectModule'
            },
            {
                path: 'file-data-object',
                loadChildren: './file-data-object/file-data-object.module#CaseServiceFileDataObjectModule'
            },
            {
                path: 'category',
                loadChildren: './category/category.module#CaseServiceCategoryModule'
            },
            {
                path: 'caze-instance',
                loadChildren: './caze-instance/caze-instance.module#CaseServiceCazeInstanceModule'
            },
            {
                path: 'caze-type',
                loadChildren: './caze-type/caze-type.module#CaseServiceCazeTypeModule'
            },
            {
                path: 'camunda-case-instance',
                loadChildren: './camunda-case-instance/camunda-case-instance.module#CaseServiceCamundaCaseInstanceModule'
            },
            {
                path: 'camunda-process-instance',
                loadChildren: './camunda-process-instance/camunda-process-instance.module#CaseServiceCamundaProcessInstanceModule'
            },
            {
                path: 'case-data-object',
                loadChildren: './case-data-object/case-data-object.module#CaseServiceCaseDataObjectModule'
            },
            {
                path: 'api-data-object',
                loadChildren: './api-data-object/api-data-object.module#CaseServiceApiDataObjectModule'
            },
            {
                path: 'api-header',
                loadChildren: './api-header/api-header.module#CaseServiceApiHeaderModule'
            },
            {
                path: 'db-data-object',
                loadChildren: './db-data-object/db-data-object.module#CaseServiceDbDataObjectModule'
            },
            {
                path: 'file-data-object',
                loadChildren: './file-data-object/file-data-object.module#CaseServiceFileDataObjectModule'
            },
            {
                path: 'category',
                loadChildren: './category/category.module#CaseServiceCategoryModule'
            }
            /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
        ])
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CaseServiceEntityModule {}
