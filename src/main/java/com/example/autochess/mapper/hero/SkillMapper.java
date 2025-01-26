package com.example.autochess.mapper.hero;

import com.example.autochess.DTO.hero.ParameterDto;
import com.example.autochess.DTO.hero.SkillDto;
import com.example.autochess.models.enams.TypeSkill;
import com.example.autochess.models.hero.ParameterSkill;
import com.example.autochess.models.hero.Skill;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.example.autochess.mapper.SaveFile.fileName;

public class SkillMapper {
    public static List<Skill> convertDtoToEntity(List<SkillDto> skillDtos) {
        if (skillDtos == null || skillDtos.isEmpty()){
            return null;
        }
        return skillDtos.stream()
                .map(SkillMapper::mapToSkill).filter(Objects::nonNull)
                .filter(skill -> !(skill.getIcon() == null &&
                        skill.getName_skill() == null &&
                        skill.getType_damage() == null &&
                        skill.getType_skill() == null &&
                        skill.getDamage_skill() == null &&
                        (skill.getDescription_skill() == null &&
                        (skill.getParameters() == null || skill.getParameters().isEmpty())))).toList();
    }
    public static Skill mapToSkill(SkillDto skillDto) {
        Skill build = Skill.builder()
                .icon(fileName(skillDto.getIcon_skill()))
                .name_skill(skillDto.getName_skill())
                .type_damage(skillDto.getType_damage())
                .type_skill((!skillDto.getType_skill().isEmpty()) ? TypeSkill.valueOf(skillDto.getType_skill()) : null)
                .description_skill(skillDto.getDescription_skill())
                .damage_skill(skillDto.getDamage_skill())
                .parameters(mapToParameterSkills(skillDto.getParameters()))
                .build();
        return isSkillValid(build) ? build : null;
    }

    private static boolean isSkillValid(Skill skill) {
        return skill.getIcon() != null ||
                (skill.getType_damage() != null && !skill.getType_damage().isEmpty()) ||
                skill.getType_skill() != null ||
                skill.getDescription_skill() != null ||
                skill.getDamage_skill() != null ||
                (skill.getParameters() != null && !skill.getParameters().isEmpty());
    }

    private static List<ParameterSkill> mapToParameterSkills(List<ParameterDto> parameters) {
        if (parameters == null || parameters.isEmpty()){
            return null;
        }
        return parameters.stream()
                .filter(parameterDto ->
                        !((parameterDto.getName_parameters() == null || parameterDto.getName_parameters().isEmpty()) &&
                                parameterDto.getCount() == null))
                .map(parameterDto -> ParameterSkill.builder()
                        .name_parameters(parameterDto.getName_parameters())
                        .count(parameterDto.getCount())
                        .build()
                )
                .collect(Collectors.toList());
    }

    public static List<SkillDto> convertEntityToDto(List<Skill> skills) {
        if (skills == null || skills.isEmpty()){
            return  null;
        }
        return skills.stream()
                .map(skill -> {
                    SkillDto skillDto = new SkillDto();
                    skillDto.setId(skill.getId());
                    skillDto.setLinc_icon(skill.getIcon());
                    skillDto.setName_skill(skill.getName_skill());
                    skillDto.setType_damage(skill.getType_damage());
                    skillDto.setType_skill(skill.getType_skill() != null ? skill.getType_skill().toString() : null);
                    skillDto.setDescription_skill(skill.getDescription_skill());
                    skillDto.setDamage_skill(skill.getDamage_skill());
                    skillDto.setParameters(mapToParameterDtos(skill.getParameters()));
                    return skillDto;
                }).toList();
    }

    private static List<ParameterDto> mapToParameterDtos(List<ParameterSkill> parameterSkills) {
        if (parameterSkills == null || parameterSkills.isEmpty()){
            return null;
        }
        return parameterSkills.stream()
                .filter(parameterSkill ->
                        !(parameterSkill.getName_parameters() == null || parameterSkill.getName_parameters().isEmpty()) && parameterSkill.getCount() != null)
                .map(parameterSkill -> {
                    ParameterDto parameterDto = new ParameterDto();
                    parameterDto.setName_parameters(parameterSkill.getName_parameters());
                    parameterDto.setCount(parameterSkill.getCount());
                    return parameterDto;
                })
                .collect(Collectors.toList());
    }
}
