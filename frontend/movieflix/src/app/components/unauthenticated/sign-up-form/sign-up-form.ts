import { Component, ViewChild } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { UserRegister } from '../../../interfaces/user';
import { AuthService } from '../../../services/auth.service';

@Component({
  selector: 'app-sign-up-form',
  imports: [RouterLink, FormsModule],
  templateUrl: './sign-up-form.html',
  styleUrl: './sign-up-form.css',
})
export class SignUpForm {
  @ViewChild('registerForm') registerForm!: NgForm;

  user: UserRegister = {
    name: '',
    email: '',
    password: '',
  };

  errorMessage: string = '';
  sucessMessage: string = '';

  constructor(
    private authService: AuthService,
    private router: Router,
  ) {}

  onSubmit() {
    if (!this.user.name || !this.user.email || !this.user.password) {
      return;
    }

    this.authService.register(this.user).subscribe({
      next: () => {
        this.sucessMessage = 'Cadastro realizado com sucesso!';
        this.errorMessage = '';

        this.registerForm.resetForm();

        setTimeout(() => {
          this.router.navigate(['/login']);
        }, 2000);
      },
      error: (error) => {
        this.errorMessage = error.error;
        this.sucessMessage = '';
      },
    });
  }
}
