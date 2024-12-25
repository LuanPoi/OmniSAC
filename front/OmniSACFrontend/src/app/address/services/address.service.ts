import { Injectable } from "@angular/core";
import { HttpOmniSACService } from "../../core/services/http.web.service";
import { AddressValidationReturn } from "../../client/models/addressValidationReturn";
import { Observable } from "rxjs";
import { Response } from "../../core/models/response";

@Injectable({
    providedIn: 'root'
})
export class AddressService {
    endpoint: string = "addresses";

    constructor(private httpOmniSACService: HttpOmniSACService) {
    }

    validatePostalCode(countryId: string, postalCode: string): Observable<Response<AddressValidationReturn, string>> {
        return this.httpOmniSACService.get(`${this.endpoint}/validatePostalCode/${countryId}/${postalCode}`);
    }
}