import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ProjetPSharedModule } from 'app/shared/shared.module';
import { PersonnageComponent } from './personnage.component';
import { PersonnageDetailComponent } from './personnage-detail.component';
import { PersonnageUpdateComponent } from './personnage-update.component';
import { PersonnageDeleteDialogComponent } from './personnage-delete-dialog.component';
import { personnageRoute } from './personnage.route';

@NgModule({
  imports: [ProjetPSharedModule, RouterModule.forChild(personnageRoute)],
  declarations: [PersonnageComponent, PersonnageDetailComponent, PersonnageUpdateComponent, PersonnageDeleteDialogComponent],
  entryComponents: [PersonnageDeleteDialogComponent],
})
export class ProjetPPersonnageModule {}
