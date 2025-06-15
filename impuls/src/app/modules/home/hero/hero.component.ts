import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../../../services/user.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-hero',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './hero.component.html',
  styleUrl: './hero.component.scss'
})
export class HeroComponent {
  constructor(public userService: UserService, private router: Router) { }

  goToDirectory() {
    this.router.navigate(['/entrepreneurship']);
  }

  goToSignUpPage() {
    if (this.userService.isAuthenticated()) {
      this.router.navigate(['/entrepreneurship_form']);
    } else {
      this.router.navigate(['/register']);
    }
  }
}
