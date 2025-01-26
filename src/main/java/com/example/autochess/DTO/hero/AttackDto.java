package com.example.autochess.DTO.hero;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttackDto {
    private Double speed_attack;
    private Double damage;
    private Double range_damage;
    private Double speed_movement;
    private Double additional_spell_damage;
    private Double mana_recovery;
}
