import { Component } from '@angular/core';
import { FeatureCardComponent } from "./feature-card/feature-card.component";

@Component({
  selector: 'app-features',
  standalone: true,
  imports: [FeatureCardComponent],
  templateUrl: './features.component.html',
  styleUrl: './features.component.scss'
})
export class FeaturesComponent {

  features = [
    {
      title: 'Búsqueda de emprendimientos',
      description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit...'
    },
    {
      title: 'Panel de control para emprendedores',
      description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit...'
    },
    {
      title: 'Eventos y noticias de emprendimiento',
      description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit...'
    },
    {
      title: 'Red de emprendedores',
      description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit...'
    }
  ];

}
