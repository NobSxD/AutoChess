package com.example.autochess.controller;

import com.example.autochess.models.ability.Ability;
import com.example.autochess.service.AbilityService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/ability")
@Slf4j
public class AbilityController {
    private final AbilityService abilityService;

    @GetMapping
    public String getTableAbility(Model model){
        model.addAttribute("abilities", abilityService.viewAbilityTable());
        return "ability/TableAbility";
    }

    @GetMapping("/add")
    private String formAbility(){
        return "ability/AddAbility";
    }

    @PostMapping("/add")
    private String addAbility(@ModelAttribute Ability ability, @RequestParam("icon_file") MultipartFile icon_file,
                              RedirectAttributes redirectAttributes){
        try {
            abilityService.saveAbility(ability, icon_file);
            redirectAttributes.addFlashAttribute("successMessage", "Абилка успешно добавлена!");
        }catch (Exception e) {

            log.error("Ошибка при добавлении героя, error: {}", e.getMessage());
            redirectAttributes.addFlashAttribute(
                    "errorMessage", "Ошибка при добавлении абилки. Пожалуйста, попробуйте снова.");
        }
        return "redirect:/ability/add";
    }

    @GetMapping("/edit/{id}")
    private String editAbility(@PathVariable Long id, Model model){
        model.addAttribute("ability",abilityService.getAbilityById(id));
        return "ability/EditAbility";
    }

    @PostMapping("/edit")
    private String editAbility(@ModelAttribute Ability ability, @RequestParam("icon_file") MultipartFile multipartFile,
                              RedirectAttributes redirectAttributes){
        try {
            abilityService.saveAbility(ability, multipartFile);
            redirectAttributes.addFlashAttribute("successMessage", "Абилка успешно добавлена!");
        }catch (Exception e) {

            log.error("Ошибка при добавлении героя, error: {}", e.getMessage());
            redirectAttributes.addFlashAttribute(
                    "errorMessage", "Ошибка при добавлении абилки. Пожалуйста, попробуйте снова.");
        }
        return "redirect:/ability/edit/" + ability.getId();
    }

    @GetMapping("/admin")
    public String getTableAdminAbility(Model model){
        model.addAttribute("abilities", abilityService.viewAbilityTable());
        return "ability/TableAdminAbility";
    }

    @PostMapping("/delete/{id}")
    private String deleteAbility(@PathVariable Long id){
        abilityService.deleteAbility(id);
        return "redirect:/ability/admin";
    }
}
