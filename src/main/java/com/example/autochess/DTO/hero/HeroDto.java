package com.example.autochess.DTO.hero;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@Builder
public class HeroDto {
    @JsonIgnore
    private Long id;
    @JsonIgnore
    private String linc_icon;
    @JsonIgnore
    private String linc_icon2;
    private String nameHero;
    private int tirHero;
    private String species;
    private List<String> classes;
    private int max_xp;
    @JsonIgnore
    private Integer max_mp;
    private String description;
    private MultipartFile icon;
    private MultipartFile icon2;
    private List<AttackDto> attacks;
    private List<ArmorDto> armors;
    private List<SkillDto> skills;
}
