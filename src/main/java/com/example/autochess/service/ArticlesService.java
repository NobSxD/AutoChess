package com.example.autochess.service;

import com.example.autochess.models.article.Article;
import com.example.autochess.models.article.VersionAutoChess;

import java.util.List;

public interface ArticlesService {
    void saveArticles(Article article);
    void deleteArticles(Long id);
    Article getArticlesId(Long id);
    List<Article> getArticlesTableAll();
    List<Article> getArticlesTableArchive();
    List<Article> getArticlesTableDraft();
    List<Article> getArticlesTablePublished();
    List<Article> getArticlesTableReview();
}
