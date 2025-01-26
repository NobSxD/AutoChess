package com.example.autochess.models.enams;

public enum ArticleStatus {
    DRAFT("Черновик"),
    REVIEW("На проверке"),
    PUBLISHED("Опубликована"),
    ARCHIVED("Архивирована");

    private final String description;

    ArticleStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return description;
    }
}
