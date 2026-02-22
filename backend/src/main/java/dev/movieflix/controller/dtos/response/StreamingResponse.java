package dev.movieflix.controller.dtos.response;

import lombok.Builder;

@Builder
public record StreamingResponse(Long id, String name) {
}
