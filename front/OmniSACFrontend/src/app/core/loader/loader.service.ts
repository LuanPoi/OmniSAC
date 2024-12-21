import { EventEmitter, Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LoaderService {

  private loading: boolean = false;

  private loaderRequestCount: number = 0;

  public onLoadStateChange: EventEmitter<boolean> = new EventEmitter();

  constructor() {
    this.resetLoader();
  }

  private resetLoader() {
    this.loaderRequestCount = 0;
    this.loading = false;
  }

  private updateLoaderRequestCount(loadRequest: boolean) {
    this.loaderRequestCount += loadRequest ? 1 : -1;
  }

  private pendingLoadRequestsExist(): boolean {
    return this.loaderRequestCount > 0
  }

  showLoader() {
    this.handleLoaderRequest(true);
  }

  hideLoader() {
    this.handleLoaderRequest(false);
  }

  forceHideLoader() {
    this.resetLoader();
  }

  private handleLoaderRequest(loadRequest: boolean) {

    this.updateLoaderRequestCount(loadRequest)

    if (this.pendingLoadRequestsExist()) {
      this.setLoading(true);
      return;
    }

    this.setLoading(false);
  }

  private setLoading(showLoader: boolean) {
    this.loading = showLoader;
    this.onLoadStateChange.emit(this.loading);
  }

  getLoading(): boolean {
    return this.loading;
  }
}
