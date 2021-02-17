import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ProjetPTestModule } from '../../../test.module';
import { TableModificateurDetailComponent } from 'app/entities/table-modificateur/table-modificateur-detail.component';
import { TableModificateur } from 'app/shared/model/table-modificateur.model';

describe('Component Tests', () => {
  describe('TableModificateur Management Detail Component', () => {
    let comp: TableModificateurDetailComponent;
    let fixture: ComponentFixture<TableModificateurDetailComponent>;
    const route = ({ data: of({ tableModificateur: new TableModificateur(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ProjetPTestModule],
        declarations: [TableModificateurDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(TableModificateurDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TableModificateurDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load tableModificateur on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tableModificateur).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
