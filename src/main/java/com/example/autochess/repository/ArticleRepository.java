package com.example.autochess.repository;

import com.example.autochess.models.enams.ArticleStatus;
import com.example.autochess.models.article.Article;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findArticlesByStatus(ArticleStatus status);
}
