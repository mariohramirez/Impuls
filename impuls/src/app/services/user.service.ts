import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../model/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private httpClient: HttpClient) { }

  registerUser(user: User): Observable<User>{
    return this.httpClient.post<User>('http://localhost:8080/api/v1/public/auth/register', user);
}
}
