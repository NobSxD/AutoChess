package com.example.autochess.controller;

import com.example.autochess.mapper.SaveFile;

import java.io.File;
import java.io.IOException;
import java.util.Collections;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/upload")
public class ImageUploadController {

    @PostMapping("/image")
    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            // Проверка наличия файла
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body("Файл не был передан!");
            }

            String failName = SaveFile.fileName(file);
            // Сохранение файла на указанную директорию
            SaveFile.saveFile(failName, file);

            // Возврат относительного URL сохранившегося изображения
            return ResponseEntity.ok(Collections.singletonMap("location", failName));

        } catch (IOException ex) {
            // Обработка ошибок ввода-вывода
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Произошла ошибка при загрузке файла: " + ex.getMessage());
        }
    }
}
