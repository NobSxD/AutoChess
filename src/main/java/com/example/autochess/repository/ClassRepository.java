package com.example.autochess.repository;

import com.example.autochess.models.hero.HeroClass;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassRepository extends JpaRepository<HeroClass, Long> {
    Optional<HeroClass> findByName(String name);
}
