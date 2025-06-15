import { Component } from '@angular/core';
import { NavlinksComponent } from './navlinks/navlinks.component';
import { ProfileMenuComponent } from './profile-menu/profile-menu.component';
import { Router } from '@angular/router';
import { UserService } from '../../../../services/user.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [NavlinksComponent, ProfileMenuComponent, CommonModule],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.scss'
})
export class NavbarComponent {
  constructor(public userService: UserService, private router: Router) { }

  goToHomePage() {
    this.router.navigate(['/']);
  }

  goToSignUpPage() {
    if (this.userService.isAuthenticated()) {
      this.router.navigate(['/entrepreneurship_form']);
    } else {
      this.router.navigate(['/register']);
    }
  }

}
