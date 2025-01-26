package com.example.autochess.DTO.hero;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArmorDto {
    private Double armor;
    private Double protection_physical;
    private Double protection_mag;
    private Double protection_effects;
    private Double protection_slowing_down;
    private Double avoidance;
    private Double restoration_health;
}
