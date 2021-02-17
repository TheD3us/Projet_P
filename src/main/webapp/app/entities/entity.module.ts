import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'images',
        loadChildren: () => import('./images/images.module').then(m => m.ProjetPImagesModule),
      },
      {
        path: 'personnage',
        loadChildren: () => import('./personnage/personnage.module').then(m => m.ProjetPPersonnageModule),
      },
      {
        path: 'table-modificateur',
        loadChildren: () => import('./table-modificateur/table-modificateur.module').then(m => m.ProjetPTableModificateurModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class ProjetPEntityModule {}
