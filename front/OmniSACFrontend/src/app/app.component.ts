import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatButtonModule } from '@angular/material/button';
import { TranslateService } from '@ngx-translate/core';
import { TranslateModule } from "@ngx-translate/core";
import { ClientRegisterForm } from './client/models/clientRegisterForm';
import { ClientService } from './client/service/client.service';
import { CountryService } from './country/services/country.service';
import { MatSelectModule } from '@angular/material/select'
import { ToastrService } from 'ngx-toastr';
import { MatDividerModule } from '@angular/material/divider';
import { Country } from './country/models/country';
import { NgxMaskDirective } from 'ngx-mask';
import { LoaderService } from './core/loader/loader.service';
import { LoaderComponent } from './core/loader/loader.component';

@Component({
  selector: 'app-root',
  imports: [ReactiveFormsModule, TranslateModule, MatFormFieldModule, MatInputModule, MatButtonModule, MatSelectModule, MatDividerModule, NgxMaskDirective, LoaderComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent implements OnInit {
  title = 'OmniSACFrontend';

  _isLoading: boolean = true;

  _countries: Country[] = [];

  _form: FormGroup;

  _postalCodeName: string | null = null;

  constructor(
    private fb: FormBuilder,
    private clientService: ClientService,
    private countryService: CountryService,
    private toastService: ToastrService,
    private loaderService: LoaderService,
    private translateService: TranslateService
  ) {
    this.translateService.addLangs(['pt']);
    this.translateService.setDefaultLang('pt');
    this.translateService.use('pt');

    this._form = this.fb.group({
      firstName: ['', [Validators.required, Validators.maxLength(30)]],
      lastName: ['', [Validators.required, Validators.maxLength(30)]],
      email: ['', [Validators.required, Validators.email, Validators.maxLength(255)]],
      password: ['', [Validators.required, Validators.maxLength(255)]],
      confirmPassword: ['', [Validators.required, Validators.maxLength(255)]],
      postalCode: ['', [Validators.required, Validators.maxLength(9)]],
      street: ['', [Validators.required, Validators.maxLength(255)]],
      number: ['', [Validators.maxLength(10)]],
      complement: ['', [Validators.maxLength(255)]],
      neighborhood: ['', [Validators.maxLength(255)]],
      city: ['', [Validators.required, Validators.maxLength(255)]],
      state: ['', [Validators.required, Validators.maxLength(255)]],
      country: ['', [Validators.required, Validators.maxLength(255)]]
    }, { validator: this.passwordMatchValidator });
  }

  ngOnInit(): void {
    this._isLoading = true;
    this.loaderService.showLoader();
    this.countryService.getCountries().subscribe({
      next: (countries) => {
        this._countries = countries;
        this._form.get('country')?.setValue(countries[0].id);
        this._form.get('country')?.disable();
        this._postalCodeName = "CEP";
        this._isLoading = false;
        this.loaderService.hideLoader();
      },
      error: (error) => {
        this.toastService.warning("Não foi possível carregar os dados de país, por favor verifique sua conexão com a internet e tente novamente.");
        this.loaderService.hideLoader();
      }
    });
  }

  passwordMatchValidator(form: FormGroup) {
    return ((form.get('password')?.value == form.get('confirmPassword')?.value) ? null : { mismatch: true });
  }

  onConfirmClicked() {
    this.loaderService.showLoader();
    var clientRegisterForm = new ClientRegisterForm({
      firstName: this._form.get('firstName')?.value,
      lastName: this._form.get('lastName')?.value,
      email: this._form.get('email')?.value,
      password: this._form.get('password')?.value,
      confirmPassword: this._form.get('confirmPassword')?.value,
      postalCode: this._form.get('postalCode')?.value,
      street: this._form.get('street')?.value,
      number: this._form.get('number')?.value,
      complement: this._form.get('complement')?.value,
      neighborhood: this._form.get('neighborhood')?.value,
      city: this._form.get('city')?.value,
      state: this._form.get('state')?.value,
      countryId: this._form.get('country')?.value
    });

    this.clientService.registerUser(clientRegisterForm).subscribe({
      next: (response) => {
        if (response.error) {
          response.errors?.forEach(error => {
            this.translateService.get(error).subscribe(translatedError => {
              this.toastService.error(translatedError);
            });
          });
        }
        if (response.success) this.toastService.success(`Usuário criado com sucesso! (Id: "${response.data?.id}")`);
        this.loaderService.hideLoader();
      },
      error: (error) => {
        this.translateService.get(error).subscribe(translatedError => {
          this.toastService.error(translatedError);
        });
      }
    });
  }

  validatePostalCode(): void {
    let postalCode: string | null = this._form.get('postalCode')?.value;
    postalCode = postalCode?.trim().replace(/\D/g, '') ?? null;
    if (postalCode == null || postalCode == "" || postalCode.length < 8) {
      this.toastService.warning("Formato de CEP inválido.", "", { timeOut: 1000 });
      return;
    }
    this.loaderService.showLoader();
    this.clientService.validatePostalCode(this._form.get('country')?.value, postalCode).subscribe((response) => {
      if (response == null) {
        this.loaderService.hideLoader();
        return;
      }

      if (response.error) {
        response.errors?.forEach(error => {
          this.translateService.get(error).subscribe(translatedError => {
            this.toastService.error(translatedError);
          });
        });
        this.loaderService.hideLoader();
        return;
      }

      this._form.get('postalCode')?.setValue(response.data?.cep);

      this._form.get('street')?.setValue(response.data?.logradouro);
      this._form.get('street')?.disable();

      this._form.get('neighborhood')?.setValue(response.data?.bairro);
      this._form.get('neighborhood')?.disable();

      this._form.get('city')?.setValue(response.data?.localidade);
      this._form.get('city')?.disable();

      this._form.get('state')?.setValue(response.data?.estado);
      this._form.get('state')?.disable();

      this._form.get('country')?.setValue("0164872e-0671-4720-93a3-4840507b0b11");
      this._form.get('country')?.disable();

      this.loaderService.hideLoader();
    });
  }
}
