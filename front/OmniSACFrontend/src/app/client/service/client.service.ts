import { Injectable } from "@angular/core";
import { HttpOmniSACService } from "../../core/services/http.web.service";
import { ClientRegisterForm } from "../models/clientRegisterForm";
import { Observable } from "rxjs";
import { AddressValidationReturn } from "../models/addressValidationReturn";
import { Client } from "../models/client";
import { Response } from "../../core/models/response";

@Injectable({
    providedIn: 'root'
})
export class ClientService {
    endpoint: string = "clients";

    constructor(private httpOmniSACService: HttpOmniSACService) {
    }

    registerUser(clientRegisterForm: ClientRegisterForm): Observable<Response<Client, string>> {
        return this.httpOmniSACService.post(
            `${this.endpoint}/register`,
            clientRegisterForm
        );
    }
}