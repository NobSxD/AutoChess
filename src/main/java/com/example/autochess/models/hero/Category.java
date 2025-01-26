package com.example.autochess.models.hero;

import com.example.autochess.models.enams.TypeCategory;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@MappedSuperclass
public abstract class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String icon;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String effectScope;

    @Column(nullable = true)
    private String speciesTrait;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "TEXT")
    private String details;

    @Enumerated(EnumType.STRING)
    private TypeCategory RasaOrClass;

    @Column
    protected int cost;
    @ManyToMany
    private List<Hero> heroes;
    public void addHero(Hero hero) {
        heroes.add(hero);
        cost += hero.getTirHero();
    }

    public void deleteHero(Hero hero) {
        heroes.remove(hero);
        cost -= hero.getTirHero();
    }

}
