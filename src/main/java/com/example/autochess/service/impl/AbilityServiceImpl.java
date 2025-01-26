package com.example.autochess.service.impl;

import com.example.autochess.mapper.SaveFile;
import com.example.autochess.models.ability.Ability;
import com.example.autochess.repository.AbilityRepository;
import com.example.autochess.service.AbilityService;

import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class AbilityServiceImpl implements AbilityService {

    private final AbilityRepository abilityRepository;
    @Override
    public void saveAbility(Ability ability, MultipartFile file) {
        try {
            String name = SaveFile.fileName(file);
            if (name != null) {
                SaveFile.saveFile(name, file);
                ability.setIcon(name);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        abilityRepository.save(ability);
    }

    @Override
    public void deleteAbility(Long id) {
        abilityRepository.deleteById(id);
    }

    @Override
    public Ability getAbilityById(Long id) {
        return abilityRepository.findById(id).orElse(null);
    }

    @Override
    public List<Ability> viewAbilityTable() {
        return abilityRepository.findAll();
    }
}
