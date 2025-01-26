package com.example.autochess.service.impl;

import com.example.autochess.models.enams.ArticleStatus;
import com.example.autochess.models.article.Article;
import com.example.autochess.repository.ArticleRepository;
import com.example.autochess.service.ArticlesService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticlesServiceImpl implements ArticlesService {
    private final ArticleRepository articleRepository;
    @Override
    public void saveArticles(Article article) {
        articleRepository.save(article);
    }

    @Override
    public void deleteArticles(Long id) {
        articleRepository.findById(id);
    }

    @Override
    public Article getArticlesId(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    @Override
    public List<Article> getArticlesTableAll() {
        return articleRepository.findAll();
    }

    @Override
    public List<Article> getArticlesTableArchive() {
        return articleRepository.findArticlesByStatus(ArticleStatus.ARCHIVED);
    }

    @Override
    public List<Article> getArticlesTableDraft() {
        return articleRepository.findArticlesByStatus(ArticleStatus.DRAFT);
    }

    @Override
    public List<Article> getArticlesTablePublished() {
        return articleRepository.findArticlesByStatus(ArticleStatus.PUBLISHED);
    }

    @Override
    public List<Article> getArticlesTableReview() {
        return articleRepository.findArticlesByStatus(ArticleStatus.REVIEW);
    }
}
