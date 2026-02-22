package dev.movieflix.controller;

import dev.movieflix.controller.interfaces.MovieController;
import dev.movieflix.controller.dtos.request.MovieRequest;
import dev.movieflix.controller.dtos.response.MovieResponse;
import dev.movieflix.entity.Movie;
import dev.movieflix.mapper.MovieMapper;
import dev.movieflix.service.MovieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movieflix/movies")
@RequiredArgsConstructor
public class MovieControllerImpl implements MovieController {
    private final MovieService service;

    @PostMapping
    public ResponseEntity<MovieResponse> save(@Valid @RequestBody MovieRequest request) {
        Movie savedMovie = service.save(MovieMapper.toMovie(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(MovieMapper.toMovieResponse(savedMovie));
    }

    @GetMapping
    public ResponseEntity<List<MovieResponse>> findAll() {
        List<MovieResponse> moviesResponse = service.findAll().stream()
                .map(MovieMapper::toMovieResponse)
                .toList();
        return ResponseEntity.ok(moviesResponse);
    }

    @GetMapping("/top5")
    public ResponseEntity<List<MovieResponse>> findTop5() {
        List<MovieResponse> response = service.findTop5()
                .stream().map(MovieMapper::toMovieResponse).toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieResponse> findById(@PathVariable Long id) {
        return service.findById(id)
                .map(movie -> ResponseEntity.ok(MovieMapper.toMovieResponse(movie)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<List<MovieResponse>> findByCategory(@RequestParam Long category) {
        List<MovieResponse> response = service.findByCategory(category)
                .stream().map(MovieMapper::toMovieResponse).toList();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieResponse> update(@PathVariable Long id, @Valid @RequestBody MovieRequest request) {
        Movie movie = MovieMapper.toMovie(request);
        return service.update(id, movie)
                .map(movieUpdated -> ResponseEntity.ok(MovieMapper.toMovieResponse(movieUpdated)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Optional<Movie> optMovie = service.findById(id);
        if (optMovie.isPresent()) {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}