import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import {MatInputModule} from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatButtonModule } from '@angular/material/button';
import { TranslateService } from '@ngx-translate/core';
import {TranslateModule} from "@ngx-translate/core";
import { ClientRegisterForm } from './client/model/clientRegisterForm.model';
import { ClientService } from './client/service/client.service';

@Component({
  selector: 'app-root',
  imports: [ReactiveFormsModule, MatFormFieldModule, MatInputModule, MatButtonModule, TranslateModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent implements OnInit {
  title = 'OmniSACFrontend';

  _form: FormGroup;

  constructor(
    private fb: FormBuilder,
    private translate: TranslateService,
    private clientService: ClientService
  ) {
    this.translate.addLangs(['pt']);
    this.translate.setDefaultLang('pt');
    this.translate.use('pt');

    this._form = this.fb.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      confirmPassword: ['', Validators.required],
      postalCode: ['', Validators.required],
      street: ['', Validators.required],
      number: [''],
      complement: [''],
      neighborhood: [''],
      city: ['', Validators.required],
      state: ['', Validators.required],
      country: ['', Validators.required]
    });
  }

  ngOnInit(): void {
  }

  passwordMatchValidator(form: FormGroup) {
    return ((form.get('password')?.value == form.get('confirmPassword')?.value) ? null : { mismatch: true });
  }

  onConfirmClicked() {
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
      country: this._form.get('country')?.value
    });

    this.clientService.registerUser(clientRegisterForm).subscribe((result) => {
      console.log("registerUserResult: " + JSON.stringify(result));
    });
  }

  validatePostalCode(): void {
    this.clientService.validatePostalCode(this._form.get('postalCode')?.value).subscribe((validation) => {
      if (validation == null) return;
      this._form.get('postalCode')?.setValue(validation.cep);
      this._form.get('postalCode')?.disable();
      
      this._form.get('street')?.setValue(validation.logradouro);
      this._form.get('street')?.disable();
      
      this._form.get('neighborhood')?.setValue(validation.bairro);
      this._form.get('neighborhood')?.disable();
      
      this._form.get('city')?.setValue(validation.localidade);
      this._form.get('city')?.disable();
      
      this._form.get('state')?.setValue(validation.estado);
      this._form.get('state')?.disable();
      
      this._form.get('country')?.setValue("Brasil");
      this._form.get('country')?.disable();
      
    });
  }
}
