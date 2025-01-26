package com.example.autochess.repository;

import com.example.autochess.models.article.VersionAutoChess;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VersionRepository extends JpaRepository<VersionAutoChess, Long> {
}
