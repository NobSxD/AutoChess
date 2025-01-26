package com.example.autochess.service;

import com.example.autochess.DTO.hero.HeroDto;
import com.example.autochess.models.hero.Hero;

import java.util.List;

public interface HeroServices {
    void saveHero(HeroDto heroDto);
    void editHero(HeroDto heroDto);

    void deleteHero(Long id);

    Hero getHeroById(Long id);
    HeroDto getHeroDtoId(Long id);

    List<List<Hero>> viewHeroTable();
}
