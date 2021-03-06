import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IPersonnage } from 'app/shared/model/personnage.model';
import { PersonnageService } from './personnage.service';
import { PersonnageDeleteDialogComponent } from './personnage-delete-dialog.component';

@Component({
  selector: 'jhi-personnage',
  templateUrl: './personnage.component.html',
})
export class PersonnageComponent implements OnInit, OnDestroy {
  personnages?: IPersonnage[];
  eventSubscriber?: Subscription;
  currentSearch: string;

  constructor(
    protected personnageService: PersonnageService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    protected activatedRoute: ActivatedRoute
  ) {
    this.currentSearch =
      this.activatedRoute.snapshot && this.activatedRoute.snapshot.queryParams['search']
        ? this.activatedRoute.snapshot.queryParams['search']
        : '';
  }

  loadAll(): void {
    if (this.currentSearch) {
      this.personnageService
        .search({
          query: this.currentSearch,
        })
        .subscribe((res: HttpResponse<IPersonnage[]>) => (this.personnages = res.body || []));
      return;
    }

    this.personnageService.query().subscribe((res: HttpResponse<IPersonnage[]>) => (this.personnages = res.body || []));
  }

  search(query: string): void {
    this.currentSearch = query;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInPersonnages();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IPersonnage): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInPersonnages(): void {
    this.eventSubscriber = this.eventManager.subscribe('personnageListModification', () => this.loadAll());
  }

  delete(personnage: IPersonnage): void {
    const modalRef = this.modalService.open(PersonnageDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.personnage = personnage;
  }
}
