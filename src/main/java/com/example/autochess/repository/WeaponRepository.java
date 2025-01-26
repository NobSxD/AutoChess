package com.example.autochess.repository;

import com.example.autochess.models.weapon.Weapon;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WeaponRepository extends JpaRepository<Weapon, Long> {
}
