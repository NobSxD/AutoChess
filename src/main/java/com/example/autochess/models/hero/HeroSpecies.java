package com.example.autochess.models.hero;

import com.example.autochess.models.enams.TypeSkill;
import com.example.autochess.models.enams.TypeCategory;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class HeroSpecies extends Category {
    @PrePersist
    public void init(){
        setType(TypeSkill.Passive.name());
        setRasaOrClass(TypeCategory.RASA);
    }

}
