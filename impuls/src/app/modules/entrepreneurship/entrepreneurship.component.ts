import { Component } from '@angular/core';
import { SearchComponent } from "./components/search/search.component";
import { DirectoriesDisplayComponent } from "./components/directories-display/directories-display.component";

@Component({
  selector: 'app-entrepreneurship',
  standalone: true,
  imports: [SearchComponent, DirectoriesDisplayComponent],
  templateUrl: './entrepreneurship.component.html',
  styleUrl: './entrepreneurship.component.scss'
})
export class EntrepreneurshipComponent {

}
