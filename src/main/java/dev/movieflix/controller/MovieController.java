package dev.movieflix.controller;

import dev.movieflix.controller.request.MovieRequest;
import dev.movieflix.controller.response.MovieResponse;
import dev.movieflix.entity.Movie;
import dev.movieflix.mapper.MovieMapper;
import dev.movieflix.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Movie", description = "Recurso responsável pelo gerenciamento dos filmes.")
public class MovieController {
    private final MovieService service;

    @Operation(summary = "Buscar lista de filmes", description = "Método responsável por retornar a lista com todos os filmes cadastrados.",
    security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "Retorna todos os filmes cadastrados",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = MovieResponse.class))))
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

    @Operation(summary = "Salvar filme", description = "Método responsável por cadastrar um novo filme.")
    @ApiResponse(responseCode = "201", description = "Filme cadastrado com sucesso.")
    @PostMapping
    public ResponseEntity<MovieResponse> save(@Valid @RequestBody MovieRequest request) {
        Movie savedMovie = service.save(MovieMapper.toMovie(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(MovieMapper.toMovieResponse(savedMovie));
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