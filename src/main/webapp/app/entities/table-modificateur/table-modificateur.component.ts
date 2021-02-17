import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ITableModificateur } from 'app/shared/model/table-modificateur.model';
import { TableModificateurService } from './table-modificateur.service';

@Component({
  selector: 'jhi-table-modificateur',
  templateUrl: './table-modificateur.component.html',
})
export class TableModificateurComponent implements OnInit, OnDestroy {
  tableModificateurs?: ITableModificateur[];
  eventSubscriber?: Subscription;
  currentSearch: string;

  constructor(
    protected tableModificateurService: TableModificateurService,
    protected eventManager: JhiEventManager,
    protected activatedRoute: ActivatedRoute
  ) {
    this.currentSearch =
      this.activatedRoute.snapshot && this.activatedRoute.snapshot.queryParams['search']
        ? this.activatedRoute.snapshot.queryParams['search']
        : '';
  }

  loadAll(): void {
    if (this.currentSearch) {
      this.tableModificateurService
        .search({
          query: this.currentSearch,
        })
        .subscribe((res: HttpResponse<ITableModificateur[]>) => (this.tableModificateurs = res.body || []));
      return;
    }

    this.tableModificateurService
      .query()
      .subscribe((res: HttpResponse<ITableModificateur[]>) => (this.tableModificateurs = res.body || []));
  }

  search(query: string): void {
    this.currentSearch = query;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInTableModificateurs();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ITableModificateur): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInTableModificateurs(): void {
    this.eventSubscriber = this.eventManager.subscribe('tableModificateurListModification', () => this.loadAll());
  }
}
