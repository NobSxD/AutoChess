package com.example.autochess.service.impl;

import com.example.autochess.DTO.category.CategoryDto;
import com.example.autochess.mapper.SaveFile;
import com.example.autochess.mapper.category.ClassMapper;
import com.example.autochess.models.hero.HeroClass;
import com.example.autochess.repository.ClassRepository;
import com.example.autochess.service.ClassService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClassServiceImpl implements ClassService {
    private final ClassRepository classRepository;
    @Override
    public void saveClass(CategoryDto categoryDto) {
        HeroClass heroClass = ClassMapper.convertDtoToEntity(categoryDto);
        if (heroClass != null){
            try {
                SaveFile.saveFile(heroClass.getIcon(), categoryDto.getIconCategory());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            classRepository.save(heroClass);
            log.info("Класс успешно сохранен");
        }
    }

    @Override
    public void deleteClass(Long id) {
        HeroClass byId = classRepository.findById(id).orElseThrow();
        byId.getHeroes().clear();
        classRepository.save(byId);
        classRepository.deleteById(id);
    }

    @Override
    public HeroClass getHeroById(Long id) {
        return classRepository.findById(id).get();
    }

    @Override
    public List<HeroClass> viewHeroClassTable() {
        return classRepository.findAll();
    }
}
