package com.example.autochess.controller;

import com.example.autochess.DTO.hero.HeroDto;
import com.example.autochess.models.hero.Hero;
import com.example.autochess.models.hero.HeroClass;
import com.example.autochess.models.hero.HeroSpecies;
import com.example.autochess.repository.ClassRepository;
import com.example.autochess.repository.RasaRepository;
import com.example.autochess.service.HeroServices;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping("/heroes")
@Slf4j
public class HeroController {
    private final HeroServices heroServices;
    private final RasaRepository rasaRepository;
    private final ClassRepository classRepository;

    @GetMapping
    public String getHeroes(Model model) {
        List<List<Hero>> lists = heroServices.viewHeroTable();
        model.addAttribute("heroes", lists);
        return "hero/TableHero";
    }

    // Форма для добавления героя
    @GetMapping("/add")
    public String addHero(Model model) {
        model.addAttribute("species", rasaRepository.findAll());
        model.addAttribute("heroClasses", classRepository.findAll());
        return "hero/AddHero";
    }

    // Метод для обработки данных формы
    @PostMapping("/add")
    public String addHero(@ModelAttribute HeroDto heroDto, RedirectAttributes redirectAttributes) {
        try {
            heroServices.saveHero(heroDto);
            redirectAttributes.addFlashAttribute("successMessage", "Герой успешно добавлен!");
        }catch (Exception e){
            log.error("Ошибка при добавлении героя, error: {}", e.getMessage());
            redirectAttributes.addFlashAttribute(
                    "errorMessage", "Ошибка при добавлении героя. Пожалуйста, попробуйте снова.");
        }
        return "redirect:/heroes/add";

    }

    @GetMapping("/edit/{id}")
    public String editHeroForm(@PathVariable Long id, Model model) {
        // Получаем героя по ID
        Hero hero = heroServices.getHeroById(id);

        // Загружаем зависимости, например, список видов и классов
        List<HeroSpecies> species = rasaRepository.findAll();
        List<HeroClass> heroClasses = classRepository.findAll();

        model.addAttribute("hero", hero);
        model.addAttribute("species", species);
        model.addAttribute("heroClasses", heroClasses);

        return "hero/EditHero"; // Страница формы редактирования
    }

    @PostMapping("/edit")
    public String saveEditedHero(@ModelAttribute HeroDto heroDto, RedirectAttributes redirectAttributes) {
        try {
            heroServices.editHero(heroDto);
            redirectAttributes.addFlashAttribute("successMessage", "Герой успешно изменен!");
        } catch (Exception e) {
            log.error("Ошибка при изменении героя, error: {}",e.getMessage());
            redirectAttributes.addFlashAttribute(
                    "errorMessage", "Ошибка при изменение героя. Пожалуйста, попробуйте снова.");
        }
        return "redirect:/heroes/edit/" + heroDto.getId();
    }

    @PostMapping("/delete/{id}")
    public String deleteHeroAndSpecies(@PathVariable Long id) {
        heroServices.deleteHero(id);
        return "redirect:/heroes";
    }

    @GetMapping("/admin")
    public String getTableHeroAdmin(Model model){
        List<List<Hero>> lists = heroServices.viewHeroTable();
        model.addAttribute("heroes", lists);
        return "hero/TableAdminHero";
    }

    @GetMapping("/{id}")
    public String getHero(@PathVariable Long id, Model model){
        model.addAttribute("hero",heroServices.getHeroById(id));
        return "hero/CardHero";
    }

    @GetMapping("/admin/{id}")
    public String getHeroAdmin(@PathVariable Long id, Model model){
        model.addAttribute("hero",heroServices.getHeroById(id));
        return "hero/CardAdminHero";
    }

}
