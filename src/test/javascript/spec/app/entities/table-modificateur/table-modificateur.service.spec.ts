import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { TableModificateurService } from 'app/entities/table-modificateur/table-modificateur.service';
import { ITableModificateur, TableModificateur } from 'app/shared/model/table-modificateur.model';

describe('Service Tests', () => {
  describe('TableModificateur Service', () => {
    let injector: TestBed;
    let service: TableModificateurService;
    let httpMock: HttpTestingController;
    let elemDefault: ITableModificateur;
    let expectedResult: ITableModificateur | ITableModificateur[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(TableModificateurService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new TableModificateur(0, 0, 0);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should return a list of TableModificateur', () => {
        const returnedFromService = Object.assign(
          {
            valeur: 1,
            modificateur: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
