import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { PersonnageService } from 'app/entities/personnage/personnage.service';
import { IPersonnage, Personnage } from 'app/shared/model/personnage.model';

describe('Service Tests', () => {
  describe('Personnage Service', () => {
    let injector: TestBed;
    let service: PersonnageService;
    let httpMock: HttpTestingController;
    let elemDefault: IPersonnage;
    let expectedResult: IPersonnage | IPersonnage[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(PersonnageService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new Personnage(0, 'AAAAAAA', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Personnage', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new Personnage()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Personnage', () => {
        const returnedFromService = Object.assign(
          {
            nom: 'BBBBBB',
            classeArmure: 1,
            bonusMaitrise: 1,
            force: 1,
            dexterite: 1,
            constitution: 1,
            intelligence: 1,
            sagesse: 1,
            charisme: 1,
            vie: 1,
            perceptionPassive: 1,
            initiative: 1,
            bonusAttaqueCAC: 1,
            bonusAttaqueDistance: 1,
            modificateurForce: 1,
            modificateurDexterite: 1,
            modificateurConstitution: 1,
            modificateurIntelligence: 1,
            modificateurSagesse: 1,
            modificateurCharisme: 1,
            niveau: 1,
            deDeVie: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Personnage', () => {
        const returnedFromService = Object.assign(
          {
            nom: 'BBBBBB',
            classeArmure: 1,
            bonusMaitrise: 1,
            force: 1,
            dexterite: 1,
            constitution: 1,
            intelligence: 1,
            sagesse: 1,
            charisme: 1,
            vie: 1,
            perceptionPassive: 1,
            initiative: 1,
            bonusAttaqueCAC: 1,
            bonusAttaqueDistance: 1,
            modificateurForce: 1,
            modificateurDexterite: 1,
            modificateurConstitution: 1,
            modificateurIntelligence: 1,
            modificateurSagesse: 1,
            modificateurCharisme: 1,
            niveau: 1,
            deDeVie: 1,
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

      it('should delete a Personnage', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
