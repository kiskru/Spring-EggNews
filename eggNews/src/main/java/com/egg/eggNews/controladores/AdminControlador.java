/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.egg.eggNews.controladores;

import com.egg.eggNews.entidades.Noticia;
import com.egg.eggNews.servicios.NoticiaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @author Kidver
 */

@Controller
@RequestMapping("/admin")
public class AdminControlador {
    
    @Autowired
    private NoticiaService noticiaService;
    
    @GetMapping("/dashboard")
    public String panelAdministrativo(){
        
        return "panel.html";
    }

    @GetMapping("/lista")
    public String lista(ModelMap modelo) {

        List<Noticia> noticias = noticiaService.listarNoticias();
        modelo.addAttribute("noticias", noticias);

        return "noticiaListaAdmin.html";
    }
}//The end
