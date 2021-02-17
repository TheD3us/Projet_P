import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITableModificateur } from 'app/shared/model/table-modificateur.model';

@Component({
  selector: 'jhi-table-modificateur-detail',
  templateUrl: './table-modificateur-detail.component.html',
})
export class TableModificateurDetailComponent implements OnInit {
  tableModificateur: ITableModificateur | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tableModificateur }) => (this.tableModificateur = tableModificateur));
  }

  previousState(): void {
    window.history.back();
  }
}
