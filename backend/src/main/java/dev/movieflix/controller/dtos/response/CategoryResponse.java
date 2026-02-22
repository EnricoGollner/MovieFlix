package dev.movieflix.controller.dtos.response;

import lombok.Builder;

@Builder
public record CategoryResponse(Long id, String name) {
}
