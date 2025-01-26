package com.example.autochess.service;

import com.example.autochess.models.weapon.Weapon;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface WeaponService {
    void saveVersion(Weapon weapon, MultipartFile icon_file);
    void deleteVersion(Long id);
    Weapon getWeaponId(Long id);
    List<List<Weapon>> getWeaponTable();

}
