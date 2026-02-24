import { Routes } from '@angular/router';
import { Home } from './pages/authenticated/home/home';
import { Login } from './pages/unauthenticated/login/login';
import { Register } from './pages/unauthenticated/register/register';
import { ForgotPassword } from './pages/unauthenticated/forgot-password/forgot-password';

export const routes: Routes = [
  { path: '', component: Home },
  { path: 'login', component: Login },
  { path: 'register', component: Register },
  { path: 'forgot-password', component: ForgotPassword },
];
