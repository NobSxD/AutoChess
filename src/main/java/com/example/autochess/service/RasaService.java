package com.example.autochess.service;

import com.example.autochess.DTO.category.CategoryDto;
import com.example.autochess.models.hero.HeroSpecies;

import java.util.List;

public interface RasaService {
    void saveRasa(CategoryDto categoryDto);
    void deleteRasa(Long id);
    HeroSpecies getHeroById(Long id);

    List<HeroSpecies> viewHeroSpeciesTable();
}
