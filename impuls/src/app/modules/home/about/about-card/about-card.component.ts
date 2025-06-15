import { Component, Input} from '@angular/core';

@Component({
  selector: 'app-about-card',
  standalone: true,
  imports: [],
  templateUrl: './about-card.component.html',
  styleUrl: './about-card.component.scss'
})
export class AboutCardComponent {
    @Input() title: string = '';     // Título de la tarjeta
    @Input() description: string = ''; // Texto descriptivo

}
