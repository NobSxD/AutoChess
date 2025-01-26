package com.example.autochess.service.impl;

import com.example.autochess.DTO.hero.HeroDto;
import com.example.autochess.mapper.hero.ArmorMapper;
import com.example.autochess.mapper.hero.AttackMapper;
import com.example.autochess.mapper.SaveFile;
import com.example.autochess.mapper.hero.SkillMapper;
import com.example.autochess.models.hero.*;
import com.example.autochess.repository.*;
import com.example.autochess.service.HeroServices;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.autochess.mapper.SaveFile.fileName;


@Service
@RequiredArgsConstructor
@Slf4j
public class HeroServicesImpl implements HeroServices {
    private final HeroRepository heroRepository;
    private final RasaRepository rasaRepository;
    private final ClassRepository classRepository;

    @Override
    public void saveHero(HeroDto heroDto) {

        HeroSpecies heroSpecies = rasaRepository.findByName(heroDto.getSpecies()).orElse(null);
        List<HeroClass> heroClasses = heroClasses(heroDto);
        List<Attack> attacks = AttackMapper.convertDtoToEntity(heroDto.getAttacks());
        List<Armor> armors = ArmorMapper.convertDtoToEntity(heroDto.getArmors());
        List<Skill> skills = SkillMapper.convertDtoToEntity(heroDto.getSkills());

        String fileName = fileName(heroDto.getIcon());
        String fileName2 = fileName(heroDto.getIcon2());

        Hero hero = Hero.builder()
                .id(heroDto.getId())
                .icon(fileName)
                .icon2(fileName2)
                .nameHero(heroDto.getNameHero())
                .tirHero(heroDto.getTirHero())
                .max_xp(heroDto.getMax_xp())
                .description(heroDto.getDescription())
                .rasa(heroSpecies)
                .classes(heroClasses)
                .attacks(attacks)
                .armors(armors)
                .skills(skills)
                .build();

        heroRepository.save(hero);
        saveRasa(heroSpecies, hero);
        saveClasses(heroClasses, hero);

        try {
            SaveFile.saveFile(fileName, heroDto.getIcon());
            SaveFile.saveFile(fileName2, heroDto.getIcon2());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void saveRasa(HeroSpecies heroSpecies, Hero hero) {
        if (heroSpecies != null) {
            heroSpecies.addHero(hero);
            rasaRepository.save(heroSpecies);
        }
    }

    private void saveClasses(List<HeroClass> heroClass, Hero hero) {
        if (heroClass != null && !heroClass.isEmpty()) {
            heroClass.forEach(heroClasses -> heroClasses.addHero(hero));
            classRepository.saveAll(heroClass);
        }
    }

    @Override
    public void editHero(HeroDto heroDto) {
        Hero oldHero = heroRepository.findById(heroDto.getId()).get();

        HeroSpecies oldHeroSpecies = oldHero.getRasa();
        List<HeroClass> oldHeroClass = oldHero.getClasses();

        HeroSpecies newHeroSpecies = rasaRepository.findByName(heroDto.getSpecies()).orElse(null);
        List<HeroClass> newHeroClasses = heroClasses(heroDto);

        List<Attack> attacks = AttackMapper.convertDtoToEntity(heroDto.getAttacks());
        List<Armor> armors = ArmorMapper.convertDtoToEntity(heroDto.getArmors());
        List<Skill> skills = SkillMapper.convertDtoToEntity(heroDto.getSkills());

        String fileName = fileName(heroDto.getIcon());
        String fileName2 = fileName(heroDto.getIcon2());

        Hero hero = Hero.builder()
                .id(heroDto.getId())
                .icon(fileName)
                .icon2(fileName2)
                .nameHero(heroDto.getNameHero())
                .tirHero(heroDto.getTirHero())
                .max_xp(heroDto.getMax_xp())
                .description(heroDto.getDescription())
                .rasa(newHeroSpecies)
                .classes(newHeroClasses)
                .attacks(attacks)
                .armors(armors)
                .skills(skills)
                .build();

        heroRepository.save(hero);

        // Проверка и обновление рассы
        if (oldHeroSpecies != null && !oldHeroSpecies.equals(newHeroSpecies)) {
            oldHeroSpecies.deleteHero(oldHero);
            rasaRepository.save(oldHeroSpecies);
        }
        if (newHeroSpecies != null && !newHeroSpecies.equals(oldHeroSpecies)) {
            newHeroSpecies.addHero(hero);
            rasaRepository.save(newHeroSpecies);
        }

        // Проверка и обновление классов
        updateHeroClasses(oldHeroClass, newHeroClasses, hero);

        try {
            SaveFile.saveFile(fileName, heroDto.getIcon());
            SaveFile.saveFile(fileName2, heroDto.getIcon2());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void updateHeroClasses(List<HeroClass> oldHeroClasses, List<HeroClass> newHeroClasses, Hero hero) {
        // Удалить героя из старых классов, если классы изменились
        if (oldHeroClasses != null) {
            for (HeroClass oldClass : oldHeroClasses) {
                if (!newHeroClasses.contains(oldClass)) {
                    oldClass.deleteHero(hero);
                    classRepository.save(oldClass);
                }
            }
        }

        // Добавить героя в новые классы, если классы изменились
        if (newHeroClasses != null && oldHeroClasses != null) {
            for (HeroClass newClass : newHeroClasses) {
                if (!oldHeroClasses.contains(newClass)) {
                    newClass.addHero(hero);
                    classRepository.save(newClass);
                }
            }
        }
    }


    private List<HeroClass> heroClasses(HeroDto heroDto) {
        if (heroDto.getClasses() == null) {
            return null;
        }
        return heroDto.getClasses().stream()
                .map(Long::parseLong)           // Получаем Optional<HeroClass>
                .map(classRepository::findById)
                .filter(Optional::isPresent)                // Фильтруем только те, которые присутствуют
                .map(Optional::get).toList();              // Собираем в список
    }

    @Override
    public void deleteHero(Long id) {
        Hero hero = heroRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Герой не найден"));
        HeroSpecies species = hero.getRasa();
        heroRepository.delete(hero);
        if (species != null) {
            species.deleteHero(hero);
        }
    }

    @Override
    public Hero getHeroById(Long id) {
        return heroRepository.findById(id).orElse(null);
    }

    @Override
    public List<List<Hero>> viewHeroTable() {
        List<Hero> all = heroRepository.findAll();
        Map<Integer, List<Hero>> groupedByTier = all.stream()
                .collect(Collectors.groupingBy(Hero::getTirHero));


        return groupedByTier.entrySet().stream()
                .sorted(Map.Entry.comparingByKey()) // Сортируем по ключу (tirHero)
                .map(entry -> {
                    List<Hero> heroesInTier = entry.getValue();

                    heroesInTier.sort(Comparator
                            .comparing((Hero hero) -> hero.getRasa() != null ? hero.getRasa().getName() : "")
                            .thenComparing(hero -> {
                                if (hero.getClasses() != null && !hero.getClasses().isEmpty()) {
                                    // Берем первый класс для сортировки
                                    return hero.getClasses().get(0).getName();
                                }
                                return ""; // Если classes пустой/null, возвращаем пустую строку для сравнения
                            })
                    );

                    return heroesInTier;
                }).toList();
    }

    @Override
    public HeroDto getHeroDtoId(Long id) {
        Hero hero = heroRepository.findById(id).orElse(new Hero());
        return HeroDto.builder()
                .id(hero.getId())
                .linc_icon(hero.getIcon())
                .tirHero(hero.getTirHero())
                .nameHero(hero.getNameHero())
                .max_xp(hero.getMax_xp())
                .max_mp(hero.getMax_mp())
                .armors(ArmorMapper.convertEntityToDto(hero.getArmors()))
                .attacks(AttackMapper.convertEntityToDto(hero.getAttacks()))
                .skills(SkillMapper.convertEntityToDto(hero.getSkills()))
                .build();
    }
}
