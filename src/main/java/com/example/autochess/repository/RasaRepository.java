package com.example.autochess.repository;

import com.example.autochess.models.hero.HeroSpecies;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RasaRepository extends JpaRepository<HeroSpecies, Long> {
    Optional<HeroSpecies> findByName(String name);
}
