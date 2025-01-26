package com.example.autochess.repository;

import com.example.autochess.models.hero.Attack;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AttackRepository extends JpaRepository<Attack, Long> {
}
