package com.iesvdc.project.inmojaen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling // Activa la programación asincrona para las tareas programadas
public class InmojaenApplication {

	public static void main(String[] args) {
		SpringApplication.run(InmojaenApplication.class, args);
	}

}
