import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'Schedule';

  isLoaded: boolean = false;
  onLoad(loaded: () => void) {
    setTimeout(() => {
      if (this.isLoaded) {
        console.debug('onload: loaded...');
        loaded();
      } else {
        this.onLoad(loaded);
        console.debug('onload: waiting...');
      }
    }, 10);
  }
}
