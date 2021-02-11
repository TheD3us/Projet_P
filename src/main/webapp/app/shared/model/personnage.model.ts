export interface IPersonnage {
  id?: number;
  nom?: string;
  classeArmure?: number;
  bonusMaitrise?: number;
  force?: number;
  dexterite?: number;
  constitution?: number;
  intelligence?: number;
  sagesse?: number;
  charisme?: number;
  deDeVie?: number;
  vie?: number;
  perceptionPassive?: number;
  initiative?: number;
  bonusAttaqueCAC?: number;
  bonusAttaqueDistance?: number;
  modificateurForce?: number;
  modificateurDexterite?: number;
  modificateurConstitution?: number;
  modificateurIntelligence?: number;
  modificateurSagesse?: number;
  modificateurCharisme?: number;
}

export class Personnage implements IPersonnage {
  constructor(
    public id?: number,
    public nom?: string,
    public classeArmure?: number,
    public bonusMaitrise?: number,
    public force?: number,
    public dexterite?: number,
    public constitution?: number,
    public intelligence?: number,
    public sagesse?: number,
    public charisme?: number,
    public deDeVie?: number,
    public vie?: number,
    public perceptionPassive?: number,
    public initiative?: number,
    public bonusAttaqueCAC?: number,
    public bonusAttaqueDistance?: number,
    public modificateurForce?: number,
    public modificateurDexterite?: number,
    public modificateurConstitution?: number,
    public modificateurIntelligence?: number,
    public modificateurSagesse?: number,
    public modificateurCharisme?: number
  ) {}
}
