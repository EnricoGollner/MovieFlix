package dev.movieflix.controller.interfaces;

import dev.movieflix.controller.dtos.request.CategoryRequest;
import dev.movieflix.controller.dtos.response.CategoryResponse;
import dev.movieflix.controller.dtos.response.MovieResponse;
import dev.movieflix.entity.Category;
import dev.movieflix.mapper.CategoryMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Category", description = "Recurso responsável pelo gerenciamento de categorias de filmes")
public interface CategoryController {
    @Operation(summary = "Salvar categoria de filmes", description = "Método responsável por cadastrar uma nova categoria de filmes.",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "201", description = "Categoria cadastrada com sucesso.",
            content = @Content(schema = @Schema(implementation = MovieResponse.class)))
    ResponseEntity<CategoryResponse> createCategory(@Valid @RequestBody CategoryRequest request);

    @Operation(summary = "Buscar lista de categorias de filmes", description = "Método responsável por retornar a lista com todas as categorias disponíveis.",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "Retorna todas as categorias de filme cadastradas.",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = MovieResponse.class))))
    ResponseEntity<List<CategoryResponse>> findAll();

    @Operation(summary = "Buscar categoria de filmes por Id", description = "Método responsável por retornar uma categoria de filmes por Id.")
    ResponseEntity<CategoryResponse> find(@PathVariable Long id);

    @Operation(summary = "Deletar categoria de filme por Id", description = "Método responsável por deletar uma categoria de filme.",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "204", description = "Categoria deletada com sucesso.",
            content = @Content())
    @ApiResponse(responseCode = "404", description = "Categoria não encontrada.", content = @Content())
    ResponseEntity<Void> delete(@PathVariable Long id);
}
