import { Component, Input } from '@angular/core';
import { EntrepreneurshipResponse, SocialNetworkResponse } from '../../../../../model/entrepreneurship';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-entrepreneurship-card',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './entrepreneurship-card.component.html',
  styleUrl: './entrepreneurship-card.component.scss'
})
export class EntrepreneurshipCardComponent {
  @Input() entrepreneurship!: EntrepreneurshipResponse;

  getSocialIcon(networkName: string): string {
    const icons: {[key: string]: string} = {
      'facebook': 'ri-facebook-fill',
      'twitter': 'ri-twitter-fill',
      'instagram': 'ri-instagram-line',
      'linkedin': 'ri-linkedin-fill'
    };
    return icons[networkName.toLowerCase()] || '';
  }

  hasSocialNetwork(networkName: string): boolean {
    return this.entrepreneurship.socialNetworkResponses?.some(
      sn => sn.name.toLowerCase() === networkName.toLowerCase()
    ) ?? false;
  }

  getSocialUrl(networkName: string): string {
    return this.entrepreneurship.socialNetworkResponses?.find(
      sn => sn.name.toLowerCase() === networkName.toLowerCase()
    )?.url || '#';
  }
}
