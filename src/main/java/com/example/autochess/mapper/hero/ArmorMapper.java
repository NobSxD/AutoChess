package com.example.autochess.mapper.hero;

import com.example.autochess.DTO.hero.ArmorDto;
import com.example.autochess.models.hero.Armor;

import java.util.List;

public class ArmorMapper {
    public static List<Armor> convertDtoToEntity(List<ArmorDto> armorDtos) {
        if (armorDtos == null || armorDtos.isEmpty()){
            return null;
        }
        return armorDtos.stream()
                .filter(dto -> !(dto.getArmor() == null &&
                        dto.getProtection_physical() == null &&
                        dto.getProtection_mag() == null &&
                        dto.getProtection_effects() == null &&
                        dto.getProtection_slowing_down() == null &&
                        dto.getAvoidance() == null &&
                        dto.getRestoration_health() == null))
                .map(dto -> Armor.builder()
                        .armor(dto.getArmor())
                        .protection_physical(dto.getProtection_physical())
                        .protection_mag(dto.getProtection_mag())
                        .protection_effects(dto.getProtection_effects())
                        .protection_slowing_down(dto.getProtection_slowing_down())
                        .avoidance(dto.getAvoidance())
                        .restoration_health(dto.getRestoration_health())
                        .build()).toList();
    }
    public static List<ArmorDto> convertEntityToDto(List<Armor> armors) {
        if (armors == null || armors.isEmpty()){
            return null;
        }
        return armors.stream()
                .map(armor -> {
                    ArmorDto dto = new ArmorDto();
                    dto.setArmor(armor.getArmor());
                    dto.setProtection_physical(armor.getProtection_physical());
                    dto.setProtection_mag(armor.getProtection_mag());
                    dto.setProtection_effects(armor.getProtection_effects());
                    dto.setProtection_slowing_down(armor.getProtection_slowing_down());
                    dto.setAvoidance(armor.getAvoidance());
                    dto.setRestoration_health(armor.getRestoration_health());
                    return dto;
                }).toList();
    }
}
