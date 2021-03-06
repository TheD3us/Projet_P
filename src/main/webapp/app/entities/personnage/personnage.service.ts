import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption, Search } from 'app/shared/util/request-util';
import { IPersonnage } from 'app/shared/model/personnage.model';

type EntityResponseType = HttpResponse<IPersonnage>;
type EntityArrayResponseType = HttpResponse<IPersonnage[]>;

@Injectable({ providedIn: 'root' })
export class PersonnageService {
  public resourceUrl = SERVER_API_URL + 'api/personnages';
  public resourceSearchUrl = SERVER_API_URL + 'api/_search/personnages';

  constructor(protected http: HttpClient) {}

  create(personnage: IPersonnage): Observable<EntityResponseType> {
    return this.http.post<IPersonnage>(this.resourceUrl, personnage, { observe: 'response' });
  }

  update(personnage: IPersonnage): Observable<EntityResponseType> {
    return this.http.put<IPersonnage>(this.resourceUrl, personnage, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IPersonnage>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IPersonnage[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: Search): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IPersonnage[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
  }
}
