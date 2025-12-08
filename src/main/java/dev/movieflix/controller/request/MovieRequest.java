package dev.movieflix.controller.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record MovieRequest(
        @Schema(type = "string", description = "Nome do filme")
        @NotBlank(message = "O título do filme é obrigatório.")
        String name,
        @Schema(type = "string", description = "Descrição do filme")
        String description,
        @Schema(type = "date", description = "Data de lançamento do filme. Ex: 10/12/2025")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        LocalDate releaseDate,
        @Schema(type = "double", description = "Score do filme. Ex: 7.9")
        double rating,
        @Schema(type = "array", description = "Lista de códigos de categorias")
        List<Long> categories,
        @Schema(type = "array", description = "Lista de códigos de serviços de streaming")
        List<Long> streamings
) {
}
