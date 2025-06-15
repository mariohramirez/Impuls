import { Component } from '@angular/core';
import { AboutCardComponent } from "./about-card/about-card.component";

@Component({
  selector: 'app-about',
  standalone: true,
  imports: [AboutCardComponent],
  templateUrl: './about.component.html',
  styleUrl: './about.component.scss'
})
export class AboutComponent {

  about = [
    {
      title: 'Conectar',
      description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit...'
    },
    {
      title: 'Crecer',
      description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit...'
    },
    {
      title: 'Emprender',
      description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit...'
    }
  ]

}
