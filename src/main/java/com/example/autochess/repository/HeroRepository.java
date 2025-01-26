package com.example.autochess.repository;

import com.example.autochess.models.hero.Hero;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HeroRepository extends JpaRepository<Hero, Long> {
}
