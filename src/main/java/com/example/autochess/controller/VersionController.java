package com.example.autochess.controller;

import com.example.autochess.models.article.VersionAutoChess;
import com.example.autochess.service.VersionService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/version")
@RequiredArgsConstructor
public class VersionController {
    private final VersionService versionService;

    @GetMapping
    public String getVersion(Model model) {
        model.addAttribute("versions", versionService.getVersionTable());
        return "version/TableVersion";
    }

    @GetMapping("/add")
    public String addVersion() {
        return "version/AddVersion";
    }

    @PostMapping("/add")
    public String addVersion(@ModelAttribute VersionAutoChess versionAutoChess) {
        versionService.saveVersion(versionAutoChess);
        return "redirect:/version";
    }

    @PostMapping("/delete/{id}")
    public String deleteVersion(@PathVariable Long id) {
        versionService.deleteVersion(id);
        return "redirect:/version";
    }

    @GetMapping("/admin")
    public String getTableAdminVersion(Model model){
        model.addAttribute("versions", versionService.getVersionTable());
        return "version/TableAdminVersion";
    }

    @GetMapping("/edit/{id}")
    public String editVersion(@PathVariable Long id, Model model) {
        model.addAttribute("version", versionService.getVersionId(id));
        return "version/EditVersion";
    }

    @PostMapping("/edit/{id}")
    public String editVersion(@ModelAttribute VersionAutoChess versionAutoChess) {
        versionService.saveVersion(versionAutoChess);
        return "redirect:/version/edit/" + versionAutoChess.getId();
    }
}
