import { Component } from '@angular/core';
import { EntrepreneurshipCardComponent } from "./entrepreneurship-card/entrepreneurship-card.component";

@Component({
  selector: 'app-directories-display',
  standalone: true,
  imports: [EntrepreneurshipCardComponent],
  templateUrl: './directories-display.component.html',
  styleUrl: './directories-display.component.scss'
})
export class DirectoriesDisplayComponent {

}
