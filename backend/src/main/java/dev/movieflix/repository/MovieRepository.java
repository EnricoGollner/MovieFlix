package dev.movieflix.repository;

import dev.movieflix.entity.Category;
import dev.movieflix.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findMovieByCategories(List<Category> categories);
    List<Movie> findTop5ByOrderByRatingDesc();
}
