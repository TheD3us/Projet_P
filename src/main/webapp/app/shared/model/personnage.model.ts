export interface IPersonnage {
  id?: number;
  nom?: string;
  vie?: number;
  classeArmure?: number;
  perceptionPassive?: number;
  bonusMaitrise?: number;
  initiative?: number;
  bonusAttaqueCAC?: number;
  bonusAttaqueDistance?: number;
  force?: number;
  dexterite?: number;
  constitution?: number;
  intelligence?: number;
  sagesse?: number;
  charisme?: number;
  modificateurForce?: number;
  modificateurDexterite?: number;
  modificateurConstitution?: number;
  modificateurCharisme?: number;
  modificateurIntelligence?: number;
  modificateurSagesse?: number;
  deDeVie?: number;
}

export class Personnage implements IPersonnage {
  constructor(
    public id?: number,
    public nom?: string,
    public vie?: number,
    public classeArmure?: number,
    public perceptionPassive?: number,
    public bonusMaitrise?: number,
    public initiative?: number,
    public bonusAttaqueCAC?: number,
    public bonusAttaqueDistance?: number,
    public force?: number,
    public dexterite?: number,
    public constitution?: number,
    public intelligence?: number,
    public sagesse?: number,
    public charisme?: number,
    public modificateurForce?: number,
    public modificateurDexterite?: number,
    public modificateurConstitution?: number,
    public modificateurCharisme?: number,
    public modificateurIntelligence?: number,
    public modificateurSagesse?: number,
    public deDeVie?: number
  ) {}
}
