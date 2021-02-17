import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ITableModificateur, TableModificateur } from 'app/shared/model/table-modificateur.model';
import { TableModificateurService } from './table-modificateur.service';
import { TableModificateurComponent } from './table-modificateur.component';
import { TableModificateurDetailComponent } from './table-modificateur-detail.component';

@Injectable({ providedIn: 'root' })
export class TableModificateurResolve implements Resolve<ITableModificateur> {
  constructor(private service: TableModificateurService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITableModificateur> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((tableModificateur: HttpResponse<TableModificateur>) => {
          if (tableModificateur.body) {
            return of(tableModificateur.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TableModificateur());
  }
}

export const tableModificateurRoute: Routes = [
  {
    path: '',
    component: TableModificateurComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'TableModificateurs',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TableModificateurDetailComponent,
    resolve: {
      tableModificateur: TableModificateurResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'TableModificateurs',
    },
    canActivate: [UserRouteAccessService],
  },
];
