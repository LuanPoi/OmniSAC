import { Injectable } from "@angular/core";
import { HttpOmniSACService } from "../../core/services/http.web.service";
import { Observable } from 'rxjs';
import { Response } from '../../core/models/response';

@Injectable({
    providedIn: 'root'
})
export class UserService {
    endpoint: string = "users";

    constructor(private httpOmniSACService: HttpOmniSACService) {
    }

    login(email: string, password: string): Observable<Response<string, string>> {
        return this.httpOmniSACService.post(
            `${this.endpoint}/login`,
            {email: email, password: password}
        );
    }
}