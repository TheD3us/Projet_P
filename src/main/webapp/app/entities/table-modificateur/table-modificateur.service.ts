import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption, Search } from 'app/shared/util/request-util';
import { ITableModificateur } from 'app/shared/model/table-modificateur.model';

type EntityResponseType = HttpResponse<ITableModificateur>;
type EntityArrayResponseType = HttpResponse<ITableModificateur[]>;

@Injectable({ providedIn: 'root' })
export class TableModificateurService {
  public resourceUrl = SERVER_API_URL + 'api/table-modificateurs';
  public resourceSearchUrl = SERVER_API_URL + 'api/_search/table-modificateurs';

  constructor(protected http: HttpClient) {}

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITableModificateur>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITableModificateur[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  search(req: Search): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITableModificateur[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
  }
}
