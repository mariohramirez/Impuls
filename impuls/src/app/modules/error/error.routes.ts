import { Routes } from '@angular/router';
import { ErrorComponent } from './error.component';
import { Error404Component } from './error404/error404.component';

export const ERROR_ROUTES: Routes = [
  {
    path: '',
    component: ErrorComponent,
    children: [
       { path: '', redirectTo: '404', pathMatch: 'full' },
      { path: '404', component: Error404Component },
      { path: '**', redirectTo: 'errors/404' }
    ]
  }
];