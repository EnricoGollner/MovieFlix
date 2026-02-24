import { Component } from '@angular/core';
import { Header } from '../../../components/unauthenticated/header/header';
import { SignUp } from '../../../components/unauthenticated/sign-up/sign-up';

@Component({
  selector: 'app-register',
  imports: [Header, SignUp],
  templateUrl: './register.html',
  styleUrl: './register.css',
})
export class Register {

}
