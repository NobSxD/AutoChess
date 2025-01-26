package com.example.autochess.DTO.category;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class CategoryDto {
    @JsonIgnore
    private Long id;
    private String name;
    private String effectScope;
    private String speciesTrait;
    private String description;
    private MultipartFile iconCategory;
    private String details;

}
