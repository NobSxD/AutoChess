package com.example.autochess.repository;

import com.example.autochess.models.hero.Skill;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRepository extends JpaRepository<Skill, Long> {
}
