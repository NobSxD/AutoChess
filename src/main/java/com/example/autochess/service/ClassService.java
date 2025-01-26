package com.example.autochess.service;

import com.example.autochess.DTO.category.CategoryDto;
import com.example.autochess.models.hero.HeroClass;

import java.util.List;

public interface ClassService {
    void saveClass(CategoryDto CategoryDto);
    void deleteClass(Long id);

    HeroClass getHeroById(Long id);

    List<HeroClass> viewHeroClassTable();
}
