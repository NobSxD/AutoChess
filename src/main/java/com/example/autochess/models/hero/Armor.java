package com.example.autochess.models.hero;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Armor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String icon;

    private Double armor;
    private Double protection_physical;
    private Double protection_mag;
    private Double protection_effects;
    private Double protection_slowing_down;
    private Double avoidance;
    private Double restoration_health;

    @ManyToOne
    private Hero hero;
}
