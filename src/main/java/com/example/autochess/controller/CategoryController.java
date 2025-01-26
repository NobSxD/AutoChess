package com.example.autochess.controller;

import com.example.autochess.DTO.category.CategoryDto;
import com.example.autochess.models.hero.Category;
import com.example.autochess.service.CategoryService;
import com.example.autochess.service.ClassService;
import com.example.autochess.service.RasaService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/categories")
@RequiredArgsConstructor
@Slf4j
public class CategoryController {
    private final ClassService classService;
    private final RasaService rasaService;
    private final CategoryService categoryService;

    @GetMapping
    public String getCategories(Model model) {
        List<Category> categories = categoryService.viewCategoryTable();
        model.addAttribute("categories", categories);
        return "category/TableCategory";
    }

    @GetMapping("/add/class")
    public String showAddCategoryForm() {
        return "category/add/AddClass";
    }

    @PostMapping("/add/class")
    public String addCategory(@ModelAttribute CategoryDto categoryDto, RedirectAttributes redirectAttributes) {
        try {
            classService.saveClass(categoryDto);
            redirectAttributes.addFlashAttribute("successMessage", "Класс успешно добавлен!");
        } catch (Exception e) {
            log.error("Ошибка при добавлении класса, error: {}", e.getMessage());
            redirectAttributes.addFlashAttribute(
                    "errorMessage", "Ошибка при добавлении класса. Пожалуйста, попробуйте снова.");
        }

        return "redirect:/categories/add/class";
    }

    @GetMapping("/add/rasa")
    public String showAddRasaForm() {
        return "category/add/AddRasa";
    }

    @PostMapping("/add/rasa")
    public String addRasa(@ModelAttribute CategoryDto categoryDto, RedirectAttributes redirectAttributes) {
        try {
            rasaService.saveRasa(categoryDto);
            redirectAttributes.addFlashAttribute("successMessage", "Раса успешно добавлен!");
        } catch (Exception e) {
            log.error("Ошибка при добавлении класса, error: {}", e.getMessage());
            redirectAttributes.addFlashAttribute(
                    "errorMessage", "Ошибка при добавлении расы. Пожалуйста, попробуйте снова.");
        }

        return "redirect:/categories/add/rasa";
    }

    @GetMapping("/RASA/{id}")
    public String getCategoryRasa(@PathVariable Long id, Model model) {
        model.addAttribute("category", categoryService.getRasa(id));
        return "category/GetRasa";
    }

    @GetMapping("/CLASS/{id}")
    public String getCategoryClass(@PathVariable Long id, Model model) {
        model.addAttribute("category", categoryService.getClass(id));
        return "category/GetClass";
    }

}
