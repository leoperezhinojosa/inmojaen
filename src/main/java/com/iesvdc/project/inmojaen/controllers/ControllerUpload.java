package com.iesvdc.project.inmojaen.controllers;

import org.springframework.web.multipart.MultipartFile;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.File;
import java.io.IOException;


/**
 * Ruta: /api
 * Controlador para subir y guardar imagenes.
 * 
 * @return String con el resultado de la operación.
 */
@RestController
@RequestMapping("/api")
public class ControllerUpload {
    
    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file) {
        try {
            // Ruta donde se guardarán las imagenes:
            String uploadDir = "uploads/";
            File uploadFolder = new File(uploadDir);

            // Crear la carpeta si no existe:
            if (!uploadFolder.exists()) {
                uploadFolder.mkdirs();
            }

            // Guardar la imagen:
            File uploadedFile = new File(uploadDir + file.getOriginalFilename());
            file.transferTo(uploadedFile);

            return "Imagen guardada correctamente.";
        } catch (IOException e) {
            e.printStackTrace();
            return "Error al guardar la imagen.";
        }
    }
    
}
