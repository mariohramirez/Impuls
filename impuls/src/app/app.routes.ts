import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: '',
    loadChildren: () => import('./modules/layout/layout.routes').then(m => m.LAYOUT_ROUTES),
  },
  {
    path: 'errors',
    loadChildren: () => import('./modules/error/error.routes').then((m) => m.ERROR_ROUTES),
  },
  {
    path: 'auth',
    loadChildren: () => import('./modules/auth/auth.routes').then((m) => m.AUTH_ROUTES),
  },
  { path: '**', redirectTo: 'errors/404' },
];
