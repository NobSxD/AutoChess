package com.example.autochess.mapper.category;

import com.example.autochess.DTO.category.CategoryDto;
import com.example.autochess.mapper.SaveFile;
import com.example.autochess.models.hero.HeroClass;
import com.example.autochess.models.hero.HeroSpecies;

import static com.example.autochess.mapper.category.CategoryCheckArgument.checkArgument;

public class ClassMapper {
    public static HeroClass convertDtoToEntity(CategoryDto categoryDto){
        if(checkArgument(categoryDto)){
            HeroClass heroClass = new  HeroClass();
            heroClass.setIcon(SaveFile.fileName(categoryDto.getIconCategory()));
            heroClass.setId(categoryDto.getId());
            heroClass.setName(categoryDto.getName());
            heroClass.setDescription(categoryDto.getDescription());
            heroClass.setSpeciesTrait(categoryDto.getSpeciesTrait());
            heroClass.setEffectScope(categoryDto.getEffectScope());
            heroClass.setDetails(categoryDto.getDetails());
            return heroClass;
        }
        return null;
    }
}
