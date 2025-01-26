package com.example.autochess.DTO.hero;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
public class SkillDto {
    @JsonIgnore
    private Long id;
    @JsonIgnore
    private String linc_icon;
    private String name_skill;
    private String type_damage;
    private String type_skill;
    private String description_skill;
    private Double damage_skill;
    private List<ParameterDto> parameters;
    private MultipartFile icon_skill;
}
