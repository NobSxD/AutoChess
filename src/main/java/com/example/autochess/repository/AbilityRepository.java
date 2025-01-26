package com.example.autochess.repository;

import com.example.autochess.models.ability.Ability;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AbilityRepository extends JpaRepository<Ability, Long> {
}
