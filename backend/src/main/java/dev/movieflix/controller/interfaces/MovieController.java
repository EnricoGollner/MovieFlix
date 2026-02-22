package dev.movieflix.controller.interfaces;

import dev.movieflix.controller.dtos.request.MovieRequest;
import dev.movieflix.controller.dtos.response.MovieResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Tag(name = "Movie", description = "Recurso responsável pelo gerenciamento dos filmes.")
public interface MovieController {
    @Operation(summary = "Salvar filme", description = "Método responsável por cadastrar um novo filme.",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "201", description = "Filme cadastrado com sucesso.",
            content = @Content(schema = @Schema(implementation = MovieResponse.class)))
    ResponseEntity<MovieResponse> save(@Valid @RequestBody MovieRequest request);

    @Operation(summary = "Buscar lista de filmes", description = "Método responsável por retornar a lista com todos os filmes cadastrados.",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "Retorna todos os filmes cadastrados",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = MovieResponse.class))))
    ResponseEntity<List<MovieResponse>> findAll();

    @Operation(summary = "Buscar lista de top 5 melhores filmes", description = "Método responsável por retornar a lista com os 5 melhores filmes cadastrados baseado em suas avaliações.",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "Retorna os top 5 filmes cadastrados.",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = MovieResponse.class))))
    ResponseEntity<List<MovieResponse>> findTop5();

    @Operation(summary = "Buscar filme por Id", description = "Método responsável por buscar filme por Id.",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "Filme encontrado com suceso",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = MovieResponse.class))))
    @ApiResponse(responseCode = "404", description = "Filme não encontrado", content = @Content())
    ResponseEntity<MovieResponse> findById(@PathVariable Long id);

    @Operation(summary = "Buscar filmes por categoria", description = "Método responsável por buscar lista de filmes por Id da categoria.",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "Filmes encontrados com suceso",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = MovieResponse.class))))
    ResponseEntity<List<MovieResponse>> findByCategory(@RequestParam Long category);

    @Operation(summary = "Alterar filme", description = "Método responsável por dados de um filme.",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "Filme alterado com sucesso.",
            content = @Content(schema = @Schema(implementation = MovieResponse.class)))
    @ApiResponse(responseCode = "404", description = "Filme não encontrado", content = @Content())
    ResponseEntity<MovieResponse> update(@PathVariable Long id, @Valid @RequestBody MovieRequest request);

    @Operation(summary = "Deletar filme por Id", description = "Método responsável por deletar um filme.",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "204", description = "Filme deletado com sucesso.",
            content = @Content())
    @ApiResponse(responseCode = "404", description = "Filme não encontrado.", content = @Content())
    ResponseEntity<Void> delete(@PathVariable Long id);
}
