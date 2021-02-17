import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ProjetPTestModule } from '../../../test.module';
import { TableModificateurComponent } from 'app/entities/table-modificateur/table-modificateur.component';
import { TableModificateurService } from 'app/entities/table-modificateur/table-modificateur.service';
import { TableModificateur } from 'app/shared/model/table-modificateur.model';

describe('Component Tests', () => {
  describe('TableModificateur Management Component', () => {
    let comp: TableModificateurComponent;
    let fixture: ComponentFixture<TableModificateurComponent>;
    let service: TableModificateurService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ProjetPTestModule],
        declarations: [TableModificateurComponent],
      })
        .overrideTemplate(TableModificateurComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TableModificateurComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TableModificateurService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new TableModificateur(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.tableModificateurs && comp.tableModificateurs[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
