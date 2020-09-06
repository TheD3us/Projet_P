export interface IImages {
  id?: number;
  name?: string;
  src?: string;
}

export class Images implements IImages {
  constructor(public id?: number, public name?: string, public src?: string) {}
}
