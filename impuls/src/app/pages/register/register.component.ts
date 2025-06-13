import { Component } from '@angular/core';
import { UserService } from '../../services/user.service';
import { User } from '../../model/user';
import {FormsModule} from '@angular/forms';


@Component({
  selector: 'app-register',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.scss'
})
export class RegisterComponent {
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

  constructor(private userService: UserService) {}

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
