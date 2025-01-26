package com.example.autochess.service;

import com.example.autochess.models.article.VersionAutoChess;

import java.util.List;

public interface VersionService {
    void saveVersion(VersionAutoChess article);
    void deleteVersion(Long id);
    VersionAutoChess getVersionId(Long id);
    List<VersionAutoChess> getVersionTable();

}
