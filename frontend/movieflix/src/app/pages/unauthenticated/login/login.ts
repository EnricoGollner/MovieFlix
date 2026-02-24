import { Component } from '@angular/core';
import { Header } from '../../../components/unauthenticated/header/header';
import { SignIn } from '../../../components/unauthenticated/sign-in/sign-in';

@Component({
  selector: 'app-login',
  imports: [Header, SignIn],
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class Login {

}
