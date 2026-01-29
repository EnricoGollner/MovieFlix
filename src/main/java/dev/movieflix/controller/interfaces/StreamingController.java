package dev.movieflix.controller.interfaces;

import dev.movieflix.controller.dtos.request.StreamingRequest;
import dev.movieflix.controller.dtos.response.MovieResponse;
import dev.movieflix.controller.dtos.response.StreamingResponse;
import dev.movieflix.entity.Streaming;
import dev.movieflix.mapper.StreamingServiceMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Streaming", description = "Recurso responsável pelo gerenciamento de serviços de streaming de filmes")
public interface StreamingController {
    @Operation(summary = "Salvar serviço de streaming de filmes", description = "Método responsável por cadastrar um novo serviço de streaming de filmes.",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "201", description = "Serviço de filmes cadastrado com sucesso.",
            content = @Content(schema = @Schema(implementation = MovieResponse.class)))
    ResponseEntity<StreamingResponse> save(@Valid @RequestBody StreamingRequest request);

    @Operation(summary = "Salvar serviço de streaming de filmes", description = "Método responsável por cadastrar um novo serviço de streaming de filmes.",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "201", description = "Serviço de streaming de filmes cadastrado com sucesso.",
            content = @Content(schema = @Schema(implementation = MovieResponse.class)))
    ResponseEntity<List<StreamingResponse>> findAll();

    @Operation(summary = "Buscar serviço de streaming filmes por Id", description = "Método responsável por retornar um serviço de streaming de filmes por Id.")
    ResponseEntity<StreamingResponse> findById(@PathVariable Long id);

    @Operation(summary = "Deletar serviço de streaming por Id", description = "Método responsável por deletar um serviço de streaming.",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "204", description = "Serviço de streaming deletado com sucesso.",
            content = @Content())
    @ApiResponse(responseCode = "404", description = "Serviço de streaming não encontrado.", content = @Content())
    ResponseEntity<Void> deleteById(@PathVariable Long id);
}
