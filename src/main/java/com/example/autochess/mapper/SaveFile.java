package com.example.autochess.mapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;

public class SaveFile {
    private static final String UPLOAD_DIR = "uploads/";

    public static void saveFile(String fileName, MultipartFile multipartFile) throws IOException {
        if (!multipartFile.isEmpty() && fileName != null) {
            Path filePath = Paths.get(UPLOAD_DIR + fileName);
            Files.createDirectories(filePath.getParent());  // Создаем директорию, если ее нет
            multipartFile.transferTo(filePath);
        }
    }

    public static String fileName(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return null;
        }
        return "/" + file.getOriginalFilename();
    }
}
