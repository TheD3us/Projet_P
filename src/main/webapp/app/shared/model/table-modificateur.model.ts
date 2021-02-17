export interface ITableModificateur {
  id?: number;
  valeur?: number;
  modificateur?: number;
}

export class TableModificateur implements ITableModificateur {
  constructor(public id?: number, public valeur?: number, public modificateur?: number) {}
}
