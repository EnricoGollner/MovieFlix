package dev.movieflix.controller.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
public record StreamingRequest(@NotEmpty(message = "O nome do serviço de streaming é obrigatório.") String name) {
}
