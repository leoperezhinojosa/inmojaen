package com.iesvdc.project.inmojaen.controllers;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.PostMapping;


/**
 * Ruta: /api/images
 * Controlador para subir y guardar imagenes.
 * 
 * @return String con el resultado de la operación.
 */
@RestController
@RequestMapping("/api/images")
public class ControllerImageUpload {
    
    @Value("${upload.dir}")
    private String uploadDir;

    @PostMapping("/upload")
    public String uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            // Comprobar que el directorio existe:
            Path path = Paths.get(uploadDir);
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }
            // Guardar la imagen:
            Path filePath = path.resolve(file.getOriginalFilename());
            file.transferTo(filePath);
            return "Imagen guardada correctamente";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al guardar la imagen";
        }
    }
    
}
