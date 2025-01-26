package com.example.autochess.service.impl;

import com.example.autochess.mapper.SaveFile;
import com.example.autochess.models.weapon.Weapon;
import com.example.autochess.repository.WeaponRepository;
import com.example.autochess.service.WeaponService;

import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class WeaponServiceImpl implements WeaponService {
    private final WeaponRepository weaponRepository;

    @Override
    public void saveVersion(Weapon weapon, MultipartFile icon_file) {
        String fileName = SaveFile.fileName(icon_file);
        try {
            SaveFile.saveFile(fileName, icon_file);
            weapon.setIcon(fileName);
            weaponRepository.save(weapon);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteVersion(Long id) {
        weaponRepository.deleteById(id);
    }

    @Override
    public Weapon getWeaponId(Long id) {
        return weaponRepository.findById(id).orElse(null);
    }

    @Override
    public List<List<Weapon>> getWeaponTable() {
        List<Weapon> all = weaponRepository.findAll();
        return IntStream.rangeClosed(1, 5)
                .mapToObj(tier -> all.stream()
                        .filter(weapon -> weapon.getTirWeapon() == tier)
                        .sorted(Comparator.comparing(Weapon::getName))
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());

    }
}
