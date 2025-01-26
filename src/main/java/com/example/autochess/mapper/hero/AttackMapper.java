package com.example.autochess.mapper.hero;

import com.example.autochess.DTO.hero.AttackDto;
import com.example.autochess.models.hero.Attack;

import java.util.List;

public class AttackMapper {
    public static List<Attack> convertDtoToEntity(List<AttackDto> attackDtos) {
        if (attackDtos == null || attackDtos.isEmpty()){
            return null;
        }
        return attackDtos.stream()
                .filter(dto -> !(dto.getSpeed_attack() == null &&
                        dto.getDamage() == null &&
                        dto.getRange_damage() == null &&
                        dto.getSpeed_movement() == null &&
                        dto.getAdditional_spell_damage() == null &&
                        dto.getMana_recovery() == null))
                .map(dto -> Attack.builder()
                        .speed_attack(dto.getSpeed_attack())
                        .damage(dto.getDamage())
                        .range_damage(dto.getRange_damage())
                        .speed_movement(dto.getSpeed_movement())
                        .additional_spell_damage(dto.getAdditional_spell_damage())
                        .mana_recovery(dto.getMana_recovery())
                        .build()).toList();
    }
    public static List<AttackDto> convertEntityToDto(List<Attack> attacks) {
        if (attacks == null || attacks.isEmpty()){
            return null;
        }
        return attacks.stream()
                .map(attack -> {
                    AttackDto dto = new AttackDto();
                    dto.setSpeed_attack(attack.getSpeed_attack());
                    dto.setDamage(attack.getDamage());
                    dto.setRange_damage(attack.getRange_damage());
                    dto.setSpeed_movement(attack.getSpeed_movement());
                    dto.setAdditional_spell_damage(attack.getAdditional_spell_damage());
                    dto.setMana_recovery(attack.getMana_recovery());
                    return dto;
                }).toList();
    }
}
