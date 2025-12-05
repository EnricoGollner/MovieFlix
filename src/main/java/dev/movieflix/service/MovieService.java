package dev.movieflix.service;

import dev.movieflix.entity.Category;
import dev.movieflix.entity.Movie;
import dev.movieflix.entity.Streaming;
import dev.movieflix.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;
    private final CategoryService categoryService;
    private final StreamingService streamingService;

    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    public List<Movie> findTop5() {
        return movieRepository.findTop5ByOrderByRatingDesc();
    }

    public Optional<Movie> findById(Long id) {
        return movieRepository.findById(id);
    }

    public List<Movie> findByCategory(Long categoryId) {
        return movieRepository.findMovieByCategories(List.of(Category.builder().id(categoryId).build()));
    }

    public Movie save(Movie newMovie) {
        newMovie.setCategories(findCategories(newMovie.getCategories()));
        newMovie.setStreamings(findServices(newMovie.getStreamings()));
        return movieRepository.save(newMovie);
    }

    public Optional<Movie> update(Movie updateMovie) {
        Optional<Movie> optMovie = findById(updateMovie.getId());
        if (optMovie.isPresent()) {
            Movie movie = optMovie.get();
            movie.setName(updateMovie.getName());
            movie.setDescription(updateMovie.getDescription());
            movie.setRating(updateMovie.getRating());
            movie.setReleaseDate(updateMovie.getReleaseDate());

            movie.getCategories().clear();
            movie.getCategories().addAll(findCategories(updateMovie.getCategories()));

            movie.getStreamings().clear();
            movie.getStreamings().addAll(findServices(updateMovie.getStreamings()));

            return Optional.of(movieRepository.save(movie));
        }
        return Optional.empty();
    }

    public void deleteById(Long id) {
        movieRepository.deleteById(id);
    }

    private List<Category> findCategories(List<Category> categories) {
        List<Category> categoriesFound = new ArrayList<>();
        categories.forEach(category -> {
            Optional<Category> optCategory = categoryService.findById(category.getId());
            optCategory.ifPresent(categoriesFound::add);
        });
        return categoriesFound;
    }

    private List<Streaming> findServices(List<Streaming> services) {
        List<Streaming> servicesFound = new ArrayList<>();
        services.forEach(service -> {
            Optional<Streaming> optStreamService = streamingService.findById(service.getId());
            optStreamService.ifPresent(servicesFound::add);
        });
        return servicesFound;
    }
}
