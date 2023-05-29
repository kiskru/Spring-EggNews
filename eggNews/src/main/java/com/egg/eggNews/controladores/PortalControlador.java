/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.egg.eggNews.controladores;

import com.egg.eggNews.Exceptions.MyException;
import com.egg.eggNews.entidades.Noticia;
import com.egg.eggNews.servicios.NoticiaService;
import com.egg.eggNews.servicios.UserService;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Kidver
 */
@Controller
@RequestMapping("/")
public class PortalControlador {

    @Autowired
    private NoticiaService noticiaService;
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index(ModelMap modelo) {

        List<Noticia> noticias = noticiaService.listarNoticias();
        modelo.addAttribute("noticias", noticias);

        return "index.html";
    }

    @GetMapping("/registrar")
    public String registrar() {
        return "registro.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam String nombre, @RequestParam String email,
            @RequestParam String password, String password2, ModelMap modelo) {

        try {
            userService.registrar(nombre, email, password, password2);
            modelo.put("exito", "Usuario registrado con exito!");
            return "index.html";
        } catch (MyException ex) {
            modelo.put("error", ex.getMessage());
            modelo.put("nombre", nombre);
            modelo.put("email", email);
            return "registro.html";
        }

    }

    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, ModelMap modelo) {
        if (error != null) {
            modelo.put("error", "Usuario o Contraseña invalidos");
        }
        return "login.html";
    }

    @GetMapping("/inicio")
    public String inicio(ModelMap modelo) {

        List<Noticia> noticias = noticiaService.listarNoticias();
        modelo.addAttribute("noticias", noticias);

        return "index.html";
    }


}//The end
