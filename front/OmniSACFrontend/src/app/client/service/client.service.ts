import { Injectable } from "@angular/core";
import { HttpOmniSACService } from "../../core/services/http.web.service";
import { ClientRegisterForm } from "../model/clientRegisterForm.model";
import { Observable } from "rxjs";
import { AddressValidationReturn } from "../model/addressValidationReturn";

@Injectable({
    providedIn: 'root'
})
export class ClientService {
    endpoint: string = "client";

    constructor(private httpOmniSACService: HttpOmniSACService) {
    }

    registerUser(clientRegisterForm: ClientRegisterForm): Observable<any> {
        return this.httpOmniSACService.post(
            `${this.endpoint}/register`,
            clientRegisterForm
        );
    }

    validatePostalCode(postalCode: string): Observable<AddressValidationReturn> {
        return this.httpOmniSACService.get(`${this.endpoint}/validatePostalCode/${postalCode}`);
    }
}