package com.example.autochess.controller;


import com.example.autochess.models.article.Article;
import com.example.autochess.service.ArticlesService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/articles")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticlesService articlesService;

    @GetMapping
    public String getArticles(Model model) {
        model.addAttribute("articles", articlesService.getArticlesTableAll());
        return "article/state/PublishedArticle";
    }
    @GetMapping("/all")
    public String getArticlesAll(Model model) {
        model.addAttribute("articles", articlesService.getArticlesTableAll());
        return "article/state/AllArticle";
    }
    @GetMapping("/archived")
    public String getArticlesArchived(Model model) {
        model.addAttribute("articles", articlesService.getArticlesTableAll());
        return "article/state/ArchivedArticle";
    }
    @GetMapping("/draft")
    public String getArticlesDraft(Model model) {
        model.addAttribute("articles", articlesService.getArticlesTableAll());
        return "article/state/DraftArticle";
    }
    @GetMapping("/review")
    public String getArticlesReview(Model model) {
        model.addAttribute("articles", articlesService.getArticlesTableAll());
        return "article/state/ReviewArticle";
    }


    // Получение формы для добавления статьи
    @GetMapping("/add")
    public String addArticle(Model model) {
        model.addAttribute("article", new Article());
        return "article/AddArticle"; // Страница с редактором
    }

    // Обработка сохранения данных из формы
    @PostMapping("/add")
    public String saveArticle(@ModelAttribute Article article) {
        articlesService.saveArticles(article);
        return "redirect:/articles/add";
    }

    @GetMapping("/edit")
    public String editArticle(Model model) {
        model.addAttribute("article", new Article());
        return "article/EditArticle"; // Страница с редактором
    }

    // Обработка сохранения данных из формы
    @PostMapping("/edit")
    public String editArticle(@ModelAttribute Article article) {
        articlesService.saveArticles(article);
        return "redirect:/articles/edit";
    }

}
