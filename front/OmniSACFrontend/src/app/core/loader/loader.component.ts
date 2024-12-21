import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { LoaderService } from './loader.service';
import { MatProgressSpinnerModule} from '@angular/material/progress-spinner'

@Component({
  selector: 'app-loader',
  templateUrl: './loader.component.html',
  styleUrls: ['./loader.component.scss'],
  imports: [MatProgressSpinnerModule]
})
export class LoaderComponent implements OnInit {

  public loadingState: boolean = false;

  constructor(
    private loader: LoaderService,
    private changeDetector: ChangeDetectorRef
  ) {
  }

  ngOnInit(): void {
    this.loader.onLoadStateChange.subscribe(loadingState => {
      this.loadingState = loadingState;
      this.changeDetector.detectChanges();
    })
  }

}
