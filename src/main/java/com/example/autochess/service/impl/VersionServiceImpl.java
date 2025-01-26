package com.example.autochess.service.impl;

import com.example.autochess.models.article.Article;
import com.example.autochess.models.article.VersionAutoChess;
import com.example.autochess.repository.VersionRepository;
import com.example.autochess.service.VersionService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VersionServiceImpl implements VersionService {

    private final VersionRepository versionRepository;
    @Override
    public void saveVersion(VersionAutoChess article) {
        versionRepository.save(article);
    }

    @Override
    public void deleteVersion(Long id) {
        versionRepository.deleteById(id);
    }

    @Override
    public VersionAutoChess getVersionId(Long id) {
        return versionRepository.findById(id).orElse(null);
    }

    @Override
    public List<VersionAutoChess> getVersionTable() {
        return versionRepository.findAll();
    }
}
