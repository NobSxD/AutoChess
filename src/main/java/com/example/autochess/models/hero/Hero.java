package com.example.autochess.models.hero;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Hero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String icon;
    private String icon2;

    private String nameHero;
    private int tirHero;
    private int max_xp;
    private int max_mp;

    private String description;

    @ManyToOne
    private HeroSpecies rasa;
    @ManyToMany
    private List<HeroClass> classes;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Attack> attacks;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Armor> armors;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Skill> skills;

    private LocalDateTime dateTime;

    @PrePersist
    private void init() {
        dateTime = LocalDateTime.now();
        max_mp = 100;
    }
}
