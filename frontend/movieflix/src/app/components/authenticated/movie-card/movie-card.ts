import { CommonModule, NgClass } from '@angular/common';
import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-movie-card',
  imports: [CommonModule, NgClass],
  templateUrl: './movie-card.html',
  styleUrl: './movie-card.css',
})
export class MovieCard {
  @Input() title: string = '';
  @Input() description: string = '';
  @Input() duration: string = '';
  @Input() ageRating: string = '';
  @Input() approvalRating: number = 0;
  @Input() providerLogo: string = '';
  @Input() isTop10: boolean = false;
  @Input() releaseDate: string = '';

  getRatingClass(): string {
    return this.approvalRating >= 50 ? 'rating-good' : 'rating-bad';
  }
}
