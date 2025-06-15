import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { User } from '../../../../model/user';
import { UserService } from '../../../../services/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-sign-up',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './sign-up.component.html',
  styleUrl: './sign-up.component.scss'
})
export class SignUpComponent {
  userData: User = {
    email: '',
    password: '',
    role: { id: 3 },
    profileRequest: {
      firstName: '',
      lastName: '',
      phone: '',
      socialNetworkRequests: [],
      addressRequests: []
    }
  };

  constructor(private userService: UserService, private router: Router) { }

  
    goToSignIn() {
      this.router.navigate(['/auth/sign-in']);
    }
  

  onSubmit() {
    this.userService.registerUser(this.userData).subscribe({
      next: (response) => {
        console.log('Registro exitoso', response);
        // Redirigir o mostrar mensaje de éxito
      },
      error: (error) => {
        console.error('Error en el registro', error);
        // Mostrar mensaje de error
      }
    });
  }


}
