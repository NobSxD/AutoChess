package com.example.autochess.models.weapon;

import com.example.autochess.models.hero.Skill;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Weapon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    private int tirWeapon;

    @OneToOne(cascade = CascadeType.ALL)
    private Skill skill;



    private String icon;
    private LocalDateTime dateTime;

    @PrePersist
    private void init(){
        dateTime = LocalDateTime.now();
    }
}
