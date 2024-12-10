package com.iesvdc.project.inmojaen.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuración para mapear la carpeta "uploads" a una URL pública
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {
    
    @Override
    public void addResourceHandlers(@SuppressWarnings("null") ResourceHandlerRegistry registry) {
        // Mapea la carpeta "uploads" a una URL pública
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:uploads/"); // Ruta absoluta o relativa al proyecto
    }
}
