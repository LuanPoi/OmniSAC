import { Injectable } from "@angular/core";
import { HttpOmniSACService } from "../../core/services/http.web.service";
import { Observable } from "rxjs";
import { Country } from "../models/country";

@Injectable({
    providedIn: 'root'
})
export class CountryService {
    endpoint: string = "countries";

    constructor(private httpOmniSACService: HttpOmniSACService) {
    }

    getCountries(): Observable<Country[]> {
        return this.httpOmniSACService.get(`${this.endpoint}`);
    }
}