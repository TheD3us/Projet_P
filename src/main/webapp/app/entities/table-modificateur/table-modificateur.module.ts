import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ProjetPSharedModule } from 'app/shared/shared.module';
import { TableModificateurComponent } from './table-modificateur.component';
import { TableModificateurDetailComponent } from './table-modificateur-detail.component';
import { tableModificateurRoute } from './table-modificateur.route';

@NgModule({
  imports: [ProjetPSharedModule, RouterModule.forChild(tableModificateurRoute)],
  declarations: [TableModificateurComponent, TableModificateurDetailComponent],
})
export class ProjetPTableModificateurModule {}
