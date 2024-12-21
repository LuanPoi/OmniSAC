import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, retry, throwError, finalize, MonoTypeOperatorFunction, of } from 'rxjs';
import { HttpErrorHandlerService } from './http-error-handler.service';
import { EnvService } from './env.service';
import { LoaderService } from '../loader/loader.service';

@Injectable({
    providedIn: 'root'
})
export class HttpOmniSACService {

    apiUrl: string | null = null;


    constructor(
        private env: EnvService,
        private http: HttpClient,
        private defaultErrorHandler: HttpErrorHandlerService,
        private loaderService: LoaderService
    ) {
        this.apiUrl = this.env.apiUrl;
    }

    public handleError(message: any, caught: Observable<any>, customCatchFunction?: (message: any, caught: Observable<any>) => void): Observable<any> {
        if (customCatchFunction !== undefined) {
            customCatchFunction(message, caught)
            return of(message);
        }
        this.defaultErrorHandler.handleErrors(message);
        console.info(message);
        console.info(caught);
        return throwError(() => message);
    }

    get(url: string, onCatch?: (message: any, caught: Observable<any>) => void, options?: any, showLoader: boolean = true): Observable<any> {
        if (showLoader)
            this.loaderService.showLoader();
        return this.http.get(this.getFullUrl(url), options).pipe(
            retry(0),
            catchError((error: any, caught: Observable<ArrayBuffer>) => this.defaultErrorHandler.handle401(error)),
            catchError((message: any, caught: Observable<ArrayBuffer>) => {
                return this.handleError(message, caught, onCatch)
            }),
            finalize(() => { if (showLoader) this.loaderService.hideLoader(); })
        );
    }

    post(url: string, data: any, onCatch?: (message: any, caught: Observable<any>) => void, options?: any, showLoader: boolean = true): Observable<any> {
        if (showLoader)
            this.loaderService.showLoader();
        return this.http.post(this.getFullUrl(url), data, options).pipe(
            retry(0),
            catchError((error: any, caught: Observable<ArrayBuffer>) => this.defaultErrorHandler.handle401(error)),
            catchError((message: any, caught: Observable<ArrayBuffer>) => this.handleError(message, caught, onCatch)),
            finalize(() => { if (showLoader) this.loaderService.hideLoader(); })
        );
    }

    put(url: string, data: any, onCatch?: (message: any, caught: Observable<any>) => void, options?: any, showLoader: boolean = true): Observable<any> {
        if (showLoader)
            this.loaderService.showLoader();
        return this.http.put(this.getFullUrl(url), data, options).pipe(
            retry(0),
            catchError((error: any, caught: Observable<ArrayBuffer>) => this.defaultErrorHandler.handle401(error)),
            catchError((message: any, caught: Observable<ArrayBuffer>) => this.handleError(message, caught, onCatch)),
            finalize(() => { if (showLoader) this.loaderService.hideLoader(); })
        );
    }

    delete(url: string, onCatch?: (message: any, caught: Observable<any>) => void, options?: any, showLoader: boolean = true): Observable<any> {
        if (showLoader)
            this.loaderService.showLoader();
        return this.http.delete(this.getFullUrl(url), options).pipe(
            retry(0),
            catchError((error: any, caught: Observable<ArrayBuffer>) => this.defaultErrorHandler.handle401(error)),
            catchError((message: any, caught: Observable<ArrayBuffer>) => this.handleError(message, caught, onCatch)),
            finalize(() => { if (showLoader) this.loaderService.hideLoader(); })
        );
    }

    private getFullUrl(url: string): string {
        return this.apiUrl + url;
    }

}
