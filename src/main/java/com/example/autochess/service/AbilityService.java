package com.example.autochess.service;

import com.example.autochess.models.ability.Ability;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface AbilityService {
    void saveAbility(Ability ability, MultipartFile file);

    void deleteAbility(Long id);

    Ability getAbilityById(Long id);

    List<Ability> viewAbilityTable();
}
