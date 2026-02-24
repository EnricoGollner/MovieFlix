import { Component } from '@angular/core';
import { AuthLayout } from '../layout/auth-layout/auth-layout';
import { SignUpForm } from '../../../components/unauthenticated/sign-up-form/sign-up-form';

@Component({
  selector: 'app-register',
  imports: [AuthLayout, SignUpForm ],
  templateUrl: './register.html',
  styleUrl: './register.css',
})
export class Register {

}
