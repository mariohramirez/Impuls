import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navlinks',
  standalone: true,
  imports: [],
  templateUrl: './navlinks.component.html',
  styleUrl: './navlinks.component.scss'
})
export class NavlinksComponent {

  constructor(private router: Router) {}
  
    goToHomePage() {
      this.router.navigate(['/']);
    }

    goToEntrepreneurshipPage() {
      this.router.navigate(['/entrepreneurship']);
    }

    goToNewsPage() {
      this.router.navigate(['/news']);
    }
    goToEventsPage() {
      this.router.navigate(['/events']);
    }

}
