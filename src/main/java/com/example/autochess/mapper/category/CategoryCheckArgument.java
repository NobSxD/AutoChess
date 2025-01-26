package com.example.autochess.mapper.category;

import com.example.autochess.DTO.category.CategoryDto;

public class CategoryCheckArgument {
    public static boolean checkArgument(CategoryDto categoryDto) {
        return !categoryDto.getIconCategory().isEmpty() ||
                categoryDto.getName() != null ||
                categoryDto.getDetails() != null ||
                categoryDto.getDescription() != null ||
                categoryDto.getEffectScope() != null ||
                categoryDto.getSpeciesTrait() != null;
    }
}
