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
    private final MovieRepository movieRespository;
    private final CategoryService categoryService;
    private final StreamingService streamingService;

    public List<Movie> findAll() {
        return movieRespository.findAll();
    }

    public Optional<Movie> findById(Long id) {
        return movieRespository.findById(id);
    }

    public List<Movie> findByCategory(Long categoryId) {
        return movieRespository.findMovieByCategories(List.of(Category.builder().id(categoryId).build()));
    }

    public Movie save(Movie newMovie) {
        newMovie.setCategories(findCategories(newMovie.getCategories()));
        newMovie.setStreamings(findServices(newMovie.getStreamings()));
        return movieRespository.save(newMovie);
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

            return Optional.of(movieRespository.save(movie));
        }
        return Optional.empty();
    }

    public void deleteById(Long id) {
        movieRespository.deleteById(id);
    }

    private List<Category> findCategories(List<Category> categories) {
        List<Category> categoriesList = new ArrayList<>();
        categories.forEach(category -> {
            Optional<Category> optCategory = categoryService.findById(category.getId());
            optCategory.ifPresent(categoriesList::add);
        });
        return categoriesList;
    }

    private List<Streaming> findServices(List<Streaming> services) {
        List<Streaming> servicesList = new ArrayList<>();
        services.forEach(service -> {
            Optional<Streaming> optStreamService = streamingService.findById(service.getId());
            optStreamService.ifPresent(servicesList::add);
        });
        return servicesList;
    }
}
