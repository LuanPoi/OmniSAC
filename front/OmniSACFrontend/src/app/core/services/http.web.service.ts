import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { finalize, Observable, retry } from 'rxjs';
import { LoaderService } from '../loader/loader.service';
import { EnvService } from './env.service';

@Injectable({
    providedIn: 'root'
})
export class HttpOmniSACService {

    apiUrl: string | null = null;


    constructor(
        private env: EnvService,
        private http: HttpClient,
        private loaderService: LoaderService
    ) {
        this.apiUrl = this.env.apiUrl;
    }

    get(url: string, onCatch?: (message: any, caught: Observable<any>) => void, options?: any, showLoader: boolean = true): Observable<any> {
        if (showLoader)
            this.loaderService.showLoader();
        return this.http.get(this.getFullUrl(url), options).pipe(
            retry(0),
            finalize(() => { if (showLoader) this.loaderService.hideLoader(); })
        );
    }

    post(url: string, data: any, onCatch?: (message: any, caught: Observable<any>) => void, options?: any, showLoader: boolean = true): Observable<any> {
        if (showLoader)
            this.loaderService.showLoader();
        return this.http.post(this.getFullUrl(url), data, options).pipe(
            retry(0),
            finalize(() => { if (showLoader) this.loaderService.hideLoader(); })
        );
    }

    put(url: string, data: any, onCatch?: (message: any, caught: Observable<any>) => void, options?: any, showLoader: boolean = true): Observable<any> {
        if (showLoader)
            this.loaderService.showLoader();
        return this.http.put(this.getFullUrl(url), data, options).pipe(
            retry(0),
            finalize(() => { if (showLoader) this.loaderService.hideLoader(); })
        );
    }

    delete(url: string, onCatch?: (message: any, caught: Observable<any>) => void, options?: any, showLoader: boolean = true): Observable<any> {
        if (showLoader)
            this.loaderService.showLoader();
        return this.http.delete(this.getFullUrl(url), options).pipe(
            retry(0),
            finalize(() => { if (showLoader) this.loaderService.hideLoader(); })
        );
    }

    private getFullUrl(url: string): string {
        return this.apiUrl + url;
    }

}
