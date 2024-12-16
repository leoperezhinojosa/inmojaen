package com.iesvdc.project.inmojaen.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.iesvdc.project.inmojaen.models.Anuncio;
import com.iesvdc.project.inmojaen.repositories.RepoAnuncio;

@Controller
public class ControllerInvitado {
    
    @Autowired
    private RepoAnuncio repoAnuncio;

    @GetMapping("/invitado")
    public String invitado(Model modelo) {
        List<Anuncio> anuncios = repoAnuncio.findAll(); 
        modelo.addAttribute("anuncios", anuncios);
        return "invitado";
    }
    
}