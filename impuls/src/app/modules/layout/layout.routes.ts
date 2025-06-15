import { Routes } from '@angular/router';
import { LayoutComponent } from './layout.component';

export const LAYOUT_ROUTES: Routes = [
  {
    path: '',
    component: LayoutComponent,
    children: [
      {
        path: 'home',
        loadComponent: () => import('../home/home.component').then(m => m.HomeComponent)
      },
      {
        path: 'entrepreneurship',
        loadComponent: () => import('../entrepreneurship/entrepreneurship.component').then(m => m.EntrepreneurshipComponent)
      },
      {
        path: 'entrepreneurship_form',
        loadComponent: () => import('../entrepreneurship/components/entrepreneurship-form/entrepreneurship-form.component').then(m => m.EntrepreneurshipFormComponent)
      },
      { path: '', redirectTo: 'home', pathMatch: 'full' }
    ]
  }
];