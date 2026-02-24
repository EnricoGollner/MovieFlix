import { Component } from '@angular/core';
import { Header } from '../../../components/authenticated/header/header';
import { MovieCard } from '../../../components/authenticated/movie-card/movie-card';

@Component({
  selector: 'app-home',
  imports: [Header, MovieCard],
  templateUrl: './home.html',
  styleUrl: './home.css',
})
export class Home {

}
