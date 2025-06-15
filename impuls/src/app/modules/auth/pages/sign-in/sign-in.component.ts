import { Component } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { User } from '../../../../model/user';
import { UserService } from '../../../../services/user.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-sign-in',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './sign-in.component.html',
  styleUrl: './sign-in.component.scss'
})
export class SignInComponent {
  constructor(private userService: UserService, private router: Router) { }

  errorMessage: string = '';

  loginData = {
    email: '',
    password: '',
  };


   goToSignUp() {
      this.router.navigate(['/auth/sign-up']);
    }

    onSubmit(form: NgForm) {
    if (form.valid) {
      this.userService.login(this.loginData.email, this.loginData.password).subscribe({
        next: () => {
          this.router.navigate(['/home']); // Redirige al dashboard
        },
        error: (err) => {
          this.errorMessage = err.error?.message || 'Error al iniciar sesión';
        },
      });
    }
  }
}
