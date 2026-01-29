package dev.movieflix.mapper;

import dev.movieflix.controller.dtos.request.CategoryRequest;
import dev.movieflix.controller.dtos.response.CategoryResponse;
import dev.movieflix.entity.Category;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CategoryMapper {
    public static Category toCategory(CategoryRequest request) {
        return Category.builder()
                .name(request.name())
                .build();
    }

    public static CategoryResponse toCategoryResponse(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
