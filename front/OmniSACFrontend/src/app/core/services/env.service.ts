import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class EnvService {

  public apiUrl = 'http://localhost:8080/api/v1/';

  constructor() {
    this.apiUrl = environment.apiUrl;
    console.log("APIURL: " + this.apiUrl);
  }
}
