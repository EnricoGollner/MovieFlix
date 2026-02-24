import { Component } from '@angular/core';
import { AuthLayout } from "../layout/auth-layout/auth-layout";
import { ForgotPasswordForm } from '../../../components/unauthenticated/forgot-password-form/forgot-password-form';

@Component({
  selector: 'app-forgot-password',
  imports: [AuthLayout, ForgotPasswordForm],
  templateUrl: './forgot-password.html',
  styleUrl: './forgot-password.css',
})
export class ForgotPassword {

}
