package com.example.autochess.service.impl;

import com.example.autochess.models.hero.Category;
import com.example.autochess.repository.ClassRepository;
import com.example.autochess.repository.RasaRepository;
import com.example.autochess.service.CategoryService;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final ClassRepository classRepository;
    private final RasaRepository rasaRepository;
    @Override
    public List<Category> viewCategoryTable() {
        List<Category> categories = new ArrayList<>();
        categories.addAll(classRepository.findAll());
        categories.addAll(rasaRepository.findAll());
        return categories;
    }

    @Override
    public Category getRasa(Long id) {
        return rasaRepository.findById(id).orElse(null);
    }

    @Override
    public Category getClass(Long id) {
        return classRepository.findById(id).orElse(null);
    }
}
