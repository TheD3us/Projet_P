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
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class ProjetPEntityModule {}
