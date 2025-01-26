package com.example.autochess.mapper.category;

import com.example.autochess.DTO.category.CategoryDto;
import com.example.autochess.mapper.SaveFile;
import com.example.autochess.models.hero.HeroSpecies;

import static com.example.autochess.mapper.category.CategoryCheckArgument.checkArgument;

public class RasaMapper {
    public static HeroSpecies convertDtoToEntity(CategoryDto categoryDto){
        if(checkArgument(categoryDto)){
            HeroSpecies heroSpecies = new  HeroSpecies();
            heroSpecies.setIcon(SaveFile.fileName(categoryDto.getIconCategory()));
            heroSpecies.setId(categoryDto.getId());
            heroSpecies.setName(categoryDto.getName());
            heroSpecies.setDescription(categoryDto.getDescription());
            heroSpecies.setSpeciesTrait(categoryDto.getSpeciesTrait());
            heroSpecies.setEffectScope(categoryDto.getEffectScope());
            heroSpecies.setDetails(categoryDto.getDetails());
            return heroSpecies;
        }
        return null;
    }
}
