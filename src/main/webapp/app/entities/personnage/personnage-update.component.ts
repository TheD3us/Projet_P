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
    vie: [null, [Validators.required, Validators.min(1)]],
    classeArmure: [null, [Validators.required]],
    perceptionPassive: [null, [Validators.required]],
    bonusMaitrise: [null, [Validators.required]],
    initiative: [null, [Validators.required]],
    bonusAttaqueCAC: [null, [Validators.required]],
    bonusAttaqueDistance: [null, [Validators.required]],
    force: [null, [Validators.required]],
    dexterite: [null, [Validators.required]],
    constitution: [null, [Validators.required]],
    intelligence: [null, [Validators.required]],
    sagesse: [null, [Validators.required]],
    charisme: [null, [Validators.required]],
    modificateurForce: [null, [Validators.required]],
    modificateurDexterite: [null, [Validators.required]],
    modificateurConstitution: [null, [Validators.required]],
    modificateurCharisme: [null, [Validators.required]],
    modificateurIntelligence: [null, [Validators.required]],
    modificateurSagesse: [null, [Validators.required]],
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
      vie: personnage.vie,
      classeArmure: personnage.classeArmure,
      perceptionPassive: personnage.perceptionPassive,
      bonusMaitrise: personnage.bonusMaitrise,
      initiative: personnage.initiative,
      bonusAttaqueCAC: personnage.bonusAttaqueCAC,
      bonusAttaqueDistance: personnage.bonusAttaqueDistance,
      force: personnage.force,
      dexterite: personnage.dexterite,
      constitution: personnage.constitution,
      intelligence: personnage.intelligence,
      sagesse: personnage.sagesse,
      charisme: personnage.charisme,
      modificateurForce: personnage.modificateurForce,
      modificateurDexterite: personnage.modificateurDexterite,
      modificateurConstitution: personnage.modificateurConstitution,
      modificateurCharisme: personnage.modificateurCharisme,
      modificateurIntelligence: personnage.modificateurIntelligence,
      modificateurSagesse: personnage.modificateurSagesse,
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
      vie: this.editForm.get(['vie'])!.value,
      classeArmure: this.editForm.get(['classeArmure'])!.value,
      perceptionPassive: this.editForm.get(['perceptionPassive'])!.value,
      bonusMaitrise: this.editForm.get(['bonusMaitrise'])!.value,
      initiative: this.editForm.get(['initiative'])!.value,
      bonusAttaqueCAC: this.editForm.get(['bonusAttaqueCAC'])!.value,
      bonusAttaqueDistance: this.editForm.get(['bonusAttaqueDistance'])!.value,
      force: this.editForm.get(['force'])!.value,
      dexterite: this.editForm.get(['dexterite'])!.value,
      constitution: this.editForm.get(['constitution'])!.value,
      intelligence: this.editForm.get(['intelligence'])!.value,
      sagesse: this.editForm.get(['sagesse'])!.value,
      charisme: this.editForm.get(['charisme'])!.value,
      modificateurForce: this.editForm.get(['modificateurForce'])!.value,
      modificateurDexterite: this.editForm.get(['modificateurDexterite'])!.value,
      modificateurConstitution: this.editForm.get(['modificateurConstitution'])!.value,
      modificateurCharisme: this.editForm.get(['modificateurCharisme'])!.value,
      modificateurIntelligence: this.editForm.get(['modificateurIntelligence'])!.value,
      modificateurSagesse: this.editForm.get(['modificateurSagesse'])!.value,
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
