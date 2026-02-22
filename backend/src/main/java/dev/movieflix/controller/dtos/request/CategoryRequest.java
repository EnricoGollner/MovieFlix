package dev.movieflix.controller.dtos.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
public record CategoryRequest(@NotEmpty(message = "O nome da categoria é obrigatório.") String name) {
}
