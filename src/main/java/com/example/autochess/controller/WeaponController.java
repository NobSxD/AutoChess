package com.example.autochess.controller;

import com.example.autochess.models.weapon.Weapon;
import com.example.autochess.service.WeaponService;

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
@RequestMapping("weapon")
@RequiredArgsConstructor
@Slf4j
public class WeaponController {
    private final WeaponService weaponService;

    @GetMapping
    public String getWeapon(Model model) {
        model.addAttribute("weapons", weaponService.getWeaponTable());
        return "weapon/TableWeapon";
    }

    @GetMapping("/add")
    public String addWeapon() {
        return "weapon/AddWeapon";
    }

    @PostMapping("/add")
    public String addWeapon(@ModelAttribute Weapon weapon, @RequestParam("icon_file") MultipartFile icon_file,
                            RedirectAttributes redirectAttributes) {
        try {
            weaponService.saveVersion(weapon, icon_file);
            redirectAttributes.addFlashAttribute("successMessage", "Предмет успешно добавлен!");
        } catch (Exception e) {
            log.error("Во время добавление предмета произошла ошибка. {}", e.getMessage());
            redirectAttributes.addFlashAttribute(
                    "errorMessage", "Ошибка при добавлении предмета. Пожалуйста, попробуйте снова.");
        }
        return "redirect:/weapon";
    }

    @PostMapping("/delete/{id}")
    public String deleteWeapon(@PathVariable Long id) {
        weaponService.deleteVersion(id);
        return "redirect:/weapon";
    }

    @GetMapping("/edit/{id}")
    public String editWeaponForm(@PathVariable Long id, Model model) {
        Weapon weapon = weaponService.getWeaponId(id);
        model.addAttribute("weapon", weapon);
        return "weapon/EditWeapon";
    }

    @PostMapping("/edit")
    public String editWeapon(@ModelAttribute("weapon") Weapon updatedWeapon, @RequestParam("icon_file") MultipartFile icon_file,
                             RedirectAttributes redirectAttributes) {
        try {
            weaponService.saveVersion(updatedWeapon, icon_file);
            redirectAttributes.addFlashAttribute("successMessage", "Предмет успешно изменен!");
        } catch (Exception e) {
            log.error("Во время изменении предмета произошла ошибка. {}", e.getMessage());
            redirectAttributes.addFlashAttribute(
                    "errorMessage", "Ошибка при изменении предмета. Пожалуйста, попробуйте снова.");
        }
        return "redirect:/weapons/edit/" + updatedWeapon.getId();
    }
}
