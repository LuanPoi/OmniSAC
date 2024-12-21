import { HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';
import { ToastrService } from 'ngx-toastr';
import { Observable, of, Subject, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class HttpErrorHandlerService {

  private _basicErrorToasts!: { [key: string]: string }

  constructor(
    private router: Router,
    private toastr: ToastrService,
    private translateService: TranslateService
  ) {
    this.translateService.get(['TOAST.ERROR.401', 'TOAST.ERROR.403', 'TOAST.ERROR.DEFAULT']).subscribe((message: { [key: string]: string }) => {
      this._basicErrorToasts = message;
    });
  }

  private defaultErrorMessage(): Observable<string> {
    return of(this._basicErrorToasts['TOAST.ERROR.DEFAULT'])
  }

  private is400Status(status: number): boolean {
    return status >= 400 && status <= 499
  }

  private hasCustomMessage(errorResponse: HttpErrorResponse): boolean {
    return errorResponse.error !== undefined
      && errorResponse.error.length > 0
      && typeof errorResponse.error[0].interfaceMessage === 'string';
  }

  private handleStatus401(): Observable<string> {
    this.router.navigate(['auth/login']);
    this.translateToast(this._basicErrorToasts['TOAST.ERROR.401']);
    return of(this._basicErrorToasts['TOAST.ERROR.401'])
  }

  private handleStatus500(errorResponse: HttpErrorResponse): Observable<string> {
    return this.translateService.get('TOAST.ERROR.500.' + errorResponse.error.message);
  }

  private handleErrorStatusCode(errorResponse: HttpErrorResponse): Observable<string> {
    const status: number = errorResponse.status;

    if (this.is400Status(status) && this.hasCustomMessage(errorResponse))
      return of(errorResponse.error[0].interfaceMessage);

    if (status === 403)
      return of(this._basicErrorToasts['TOAST.ERROR.403'])

    if (status === 500)
      return this.handleStatus500(errorResponse);

    return this.defaultErrorMessage();
  }

  private handleErrorAndGetMessage(errorResponse: any): Observable<string> {
    if (typeof errorResponse === 'string')
      return of(errorResponse);

    if (errorResponse instanceof HttpErrorResponse)
      return this.handleErrorStatusCode(errorResponse)

    return this.defaultErrorMessage();
  }

  handleErrors(errorResponse: any, customErrorHandler?: (message: string) => void): Observable<any> {
    console.log(errorResponse)

    let errorObservable = this.handleErrorAndGetMessage(errorResponse);

    errorObservable.subscribe(
      message => customErrorHandler ? customErrorHandler(message) : this.translateToast(message)
    );

    return errorObservable;
  }

  translateToast(message:string){
    this.translateService.get(message).subscribe(m => this.toastr.error(m));
  }

  handle401(errorResponse: any): Observable<any> {
    if (errorResponse instanceof HttpErrorResponse && errorResponse.status === 401)
      return this.handleStatus401();
    return throwError(() => errorResponse);
  }
}