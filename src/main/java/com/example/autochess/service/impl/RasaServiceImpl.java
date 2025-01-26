package com.example.autochess.service.impl;

import com.example.autochess.DTO.category.CategoryDto;
import com.example.autochess.mapper.SaveFile;
import com.example.autochess.mapper.category.RasaMapper;
import com.example.autochess.models.hero.HeroSpecies;
import com.example.autochess.repository.RasaRepository;
import com.example.autochess.service.RasaService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RasaServiceImpl implements RasaService {
    private final RasaRepository rasaRepository;
    @Override
    public void saveRasa(CategoryDto categoryDto) {
        HeroSpecies heroSpecies = RasaMapper.convertDtoToEntity(categoryDto);
        if (heroSpecies != null){
            try {
                SaveFile.saveFile(heroSpecies.getIcon(), categoryDto.getIconCategory());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            rasaRepository.save(heroSpecies);
            log.info("Класс успешно сохранен");
        }
    }

    @Override
    public void deleteRasa(Long id) {
        rasaRepository.deleteById(id);
    }

    @Override
    public HeroSpecies getHeroById(Long id) {
        return rasaRepository.findById(id).orElse(null);
    }

    @Override
    public List<HeroSpecies> viewHeroSpeciesTable() {
        return rasaRepository.findAll();
    }
}
