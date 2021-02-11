import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IPersonnage, Personnage } from 'app/shared/model/personnage.model';
import { PersonnageService } from './personnage.service';

@Component({
  selector: 'jhi-personnage-update',
  templateUrl: './personnage-update.component.html',
})
export class PersonnageUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    nom: [null, [Validators.required, Validators.minLength(3)]],
    classeArmure: [null, [Validators.required]],
    bonusMaitrise: [null, [Validators.required]],
    force: [null, [Validators.required]],
    dexterite: [null, [Validators.required]],
    constitution: [null, [Validators.required]],
    intelligence: [null, [Validators.required]],
    sagesse: [null, [Validators.required]],
    charisme: [null, [Validators.required]],
    vie: [null, [Validators.min(1)]],
    perceptionPassive: [],
    initiative: [],
    bonusAttaqueCAC: [],
    bonusAttaqueDistance: [],
    modificateurForce: [],
    modificateurDexterite: [],
    modificateurConstitution: [],
    modificateurIntelligence: [],
    modificateurSagesse: [],
    modificateurCharisme: [],
    niveau: [null, [Validators.required, Validators.min(1)]],
    deDeVie: [null, [Validators.required]],
  });

  constructor(protected personnageService: PersonnageService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ personnage }) => {
      this.updateForm(personnage);
    });
  }

  updateForm(personnage: IPersonnage): void {
    this.editForm.patchValue({
      id: personnage.id,
      nom: personnage.nom,
      classeArmure: personnage.classeArmure,
      bonusMaitrise: personnage.bonusMaitrise,
      force: personnage.force,
      dexterite: personnage.dexterite,
      constitution: personnage.constitution,
      intelligence: personnage.intelligence,
      sagesse: personnage.sagesse,
      charisme: personnage.charisme,
      vie: personnage.vie,
      perceptionPassive: personnage.perceptionPassive,
      initiative: personnage.initiative,
      bonusAttaqueCAC: personnage.bonusAttaqueCAC,
      bonusAttaqueDistance: personnage.bonusAttaqueDistance,
      modificateurForce: personnage.modificateurForce,
      modificateurDexterite: personnage.modificateurDexterite,
      modificateurConstitution: personnage.modificateurConstitution,
      modificateurIntelligence: personnage.modificateurIntelligence,
      modificateurSagesse: personnage.modificateurSagesse,
      modificateurCharisme: personnage.modificateurCharisme,
      niveau: personnage.niveau,
      deDeVie: personnage.deDeVie,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const personnage = this.createFromForm();
    if (personnage.id !== undefined) {
      this.subscribeToSaveResponse(this.personnageService.update(personnage));
    } else {
      this.subscribeToSaveResponse(this.personnageService.create(personnage));
    }
  }

  private createFromForm(): IPersonnage {
    return {
      ...new Personnage(),
      id: this.editForm.get(['id'])!.value,
      nom: this.editForm.get(['nom'])!.value,
      classeArmure: this.editForm.get(['classeArmure'])!.value,
      bonusMaitrise: this.editForm.get(['bonusMaitrise'])!.value,
      force: this.editForm.get(['force'])!.value,
      dexterite: this.editForm.get(['dexterite'])!.value,
      constitution: this.editForm.get(['constitution'])!.value,
      intelligence: this.editForm.get(['intelligence'])!.value,
      sagesse: this.editForm.get(['sagesse'])!.value,
      charisme: this.editForm.get(['charisme'])!.value,
      vie: this.editForm.get(['vie'])!.value,
      perceptionPassive: this.editForm.get(['perceptionPassive'])!.value,
      initiative: this.editForm.get(['initiative'])!.value,
      bonusAttaqueCAC: this.editForm.get(['bonusAttaqueCAC'])!.value,
      bonusAttaqueDistance: this.editForm.get(['bonusAttaqueDistance'])!.value,
      modificateurForce: this.editForm.get(['modificateurForce'])!.value,
      modificateurDexterite: this.editForm.get(['modificateurDexterite'])!.value,
      modificateurConstitution: this.editForm.get(['modificateurConstitution'])!.value,
      modificateurIntelligence: this.editForm.get(['modificateurIntelligence'])!.value,
      modificateurSagesse: this.editForm.get(['modificateurSagesse'])!.value,
      modificateurCharisme: this.editForm.get(['modificateurCharisme'])!.value,
      niveau: this.editForm.get(['niveau'])!.value,
      deDeVie: this.editForm.get(['deDeVie'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPersonnage>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
