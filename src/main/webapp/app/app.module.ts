import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import './vendor';
import { ProjetPSharedModule } from 'app/shared/shared.module';
import { ProjetPCoreModule } from 'app/core/core.module';
import { ProjetPAppRoutingModule } from './app-routing.module';
import { ProjetPHomeModule } from './home/home.module';
import { ProjetPEntityModule } from './entities/entity.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { MainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ErrorComponent } from './layouts/error/error.component';

@NgModule({
  imports: [
    BrowserModule,
    ProjetPSharedModule,
    ProjetPCoreModule,
    ProjetPHomeModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    ProjetPEntityModule,
    ProjetPAppRoutingModule,
  ],
  declarations: [MainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, FooterComponent],
  bootstrap: [MainComponent],
})
export class ProjetPAppModule {}
