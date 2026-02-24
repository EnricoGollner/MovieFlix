import { Component } from '@angular/core';
import { AuthLayout } from "../layout/auth-layout/auth-layout";
import { SignInForm } from '../../../components/unauthenticated/sign-in-form/sign-in-form';

@Component({
  selector: 'app-login',
  imports: [AuthLayout, SignInForm],
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class Login {

}
