package com.example.autochess.models.hero;

import com.example.autochess.models.enams.TypeSkill;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String icon;
    private String name_skill;

    private String type_damage;

    @Enumerated(value = EnumType.STRING)
    private TypeSkill type_skill;

    @Column(length = 1000)
    private String description_skill;
    private Double damage_skill;
    private int cast_mp;

    @OneToMany(cascade = CascadeType.ALL)
    private List<ParameterSkill> parameters;
    @ManyToOne
    private Hero hero;

    @PrePersist
    private void init() {
        cast_mp = 100;
    }

}
