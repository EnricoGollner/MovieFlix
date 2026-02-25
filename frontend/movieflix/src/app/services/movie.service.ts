import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Category, Movie, StreamingService } from '../interfaces/movie';
import { Observable } from 'rxjs';
import { environment } from '../environments/environment.development';

@Injectable({
  providedIn: 'root',
})
export class MovieService {
    private apiURL = environment.apiURL;

  constructor(private http: HttpClient) {}

  getAllMovies(): Observable<Movie[]> {
    return this.http.get<Movie[]>(`${this.apiURL}/movieflix/movies`);
  }

  getMovieById(id: number): Observable<Movie> {
    return this.http.get<Movie>(`${this.apiURL}/movieflix/movies/${id}`);
  }

  createMovie(movie: Omit<Movie, 'id'>): Observable<Movie> {
    return this.http.post<Movie>(`${this.apiURL}/movieflix/movie`, movie);
  }

  updateMovie(id: number, movie: Partial<Movie>): Observable<Movie> {
    return this.http.put<Movie>(`${this.apiURL}/movieflix/movies/${id}`, movie);
  }

  deleteMovie(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiURL}/movieflix/movies/${id}`);
  }

  getAllCategories(): Observable<Category[]> {
    return this.http.get<Category[]>(`${this.apiURL}/movieflix/category`);
  }

  getAllStreamServices(): Observable<StreamingService[]> {
    return this.http.get<StreamingService[]>(`${this.apiURL}/movieflix/streaming`);
  }
}
