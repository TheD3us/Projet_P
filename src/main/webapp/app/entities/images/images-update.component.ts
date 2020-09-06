import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IImages, Images } from 'app/shared/model/images.model';
import { ImagesService } from './images.service';

@Component({
  selector: 'jhi-images-update',
  templateUrl: './images-update.component.html',
})
export class ImagesUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.required, Validators.minLength(3), Validators.maxLength(30)]],
    src: [null, [Validators.required]],
  });

  constructor(protected imagesService: ImagesService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ images }) => {
      this.updateForm(images);
    });
  }

  updateForm(images: IImages): void {
    this.editForm.patchValue({
      id: images.id,
      name: images.name,
      src: images.src,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const images = this.createFromForm();
    if (images.id !== undefined) {
      this.subscribeToSaveResponse(this.imagesService.update(images));
    } else {
      this.subscribeToSaveResponse(this.imagesService.create(images));
    }
  }

  private createFromForm(): IImages {
    return {
      ...new Images(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      src: this.editForm.get(['src'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IImages>>): void {
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
