import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../model/user';
import { Router } from '@angular/router';
import { Observable, catchError, tap } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private httpClient: HttpClient, private router: Router) { }

  registerUser(user: User): Observable<User>{
    return this.httpClient.post<User>('http://localhost:8080/api/v1/public/auth/register', user);
}

login(email: string, password: string): Observable<any> {
    return this.httpClient.post('http://localhost:8080/api/v1/public/auth/login', { email, password }).pipe(
      tap((response: any) => {
        if (response.jwt) {
          localStorage.setItem('token', response.jwt); // Guarda el token
        }
      }),
      catchError((err) => {
        throw err; // Maneja errores en el componente
      })
    );
  }

  isAuthenticated(): boolean {
    const token = localStorage.getItem('token');
    return !!token; // Devuelve true si existe token, false si no
  }


}
