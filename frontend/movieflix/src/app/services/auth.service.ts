import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, map, Observable, of } from 'rxjs';
import { User, UserRegister, UserResponse } from '../interfaces/user';
import { environment } from '../environments/environment.development';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
    private apiURL = environment.apiURL;

  constructor(private http: HttpClient) {}

  login(user: User): Observable<UserResponse> {
    return this.http.post<UserResponse>(`${this.apiURL}/movieflix/auth/login`, user);
  }

  register(user: UserRegister): Observable<UserResponse> {
    return this.http.post<UserResponse>(`${this.apiURL}/movieflix/auth/register`, user);
  }

  validateToken(): Observable<boolean> {
    const token = this.getToken();

    if (!token) {
      return of(false);
    }

    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);

    return this.http.head(`${this.apiURL}/movieflix/auth/validate`, { headers, observe: 'response' }).pipe(
      map((response) => response.status === 200),
      catchError(() => of(false)),
    );
  }

  getToken(): string | null {
    return localStorage.getItem('token');
  }

  isAuthenticated(): boolean {
    return !!this.getToken();
  }

  logout(): void {
    localStorage.removeItem('token');
  }
}
