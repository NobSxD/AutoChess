package com.example.autochess.service;

import com.example.autochess.DTO.category.CategoryDto;
import com.example.autochess.models.hero.Category;

import java.util.List;

public interface CategoryService {
    List<Category> viewCategoryTable();
    Category getRasa(Long id);
    Category getClass(Long id);
}
