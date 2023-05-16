/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.egg.eggNews.controladores;

import com.egg.eggNews.Exceptions.MyException;
import com.egg.eggNews.entidades.Noticia;
import com.egg.eggNews.servicios.NoticiaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Kidver
 */
@Controller
@RequestMapping("/noticia") //localhost:8080/noticia
public class NoticiaControlador {

    @Autowired
    private NoticiaService noticiaService;

    @GetMapping("/registrar") //localhost:8080/noticia/registrar
    public String registrar(ModelMap modelo) {

        List<Noticia> noticias = noticiaService.listarNoticias();
        modelo.addAttribute("noticias", noticias);// muestra una lista en el formulario

        return "noticiaForm.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam String titulo, @RequestParam String contenido, ModelMap modelo) {

        try {
            noticiaService.crearNoticia(titulo, contenido); // retorna al index si todo esta bien
            modelo.put("exito", "La noticia ha sido cargada con exito");
        } catch (MyException e) {
            List<Noticia> noticias = noticiaService.listarNoticias();
            modelo.addAttribute("noticias", noticias);
            modelo.put("error", e.getMessage()); // muestra el mensaje que tiene la exeption configurada

            return "noticiaForm.html";
        }
        return "index.html";

    }

    @GetMapping("/lista")
    public String lista(ModelMap modelo) {

        List<Noticia> noticias = noticiaService.listarNoticias();
        modelo.addAttribute("noticias", noticias);

        return "noticiaLista.html";
    }

    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable Long id, ModelMap modelo) {
        modelo.put("noticia", noticiaService.getOne(id));
        return "noticiaModificar.html";
    }

    @PostMapping("/modificar/{id}")
    public String modificar(@PathVariable Long id, String titulo, String contenido, ModelMap modelo) {

        try {
            noticiaService.modificarNoticia(id, titulo, contenido);
            modelo.put("exito", "La noticia ha sido cargada con exito");
            return "redirect:/noticia/lista";
        } catch (MyException ex) {
            modelo.put("error", ex.getMessage());
            return "noticiaModificar.html";
        }

    }

    @GetMapping("/vista/{id}")
    public String vista(@PathVariable Long id, ModelMap modelo) {
        Noticia noticia = noticiaService.getOne(id);
        modelo.put("noticia", noticia);

        return "noticiaVista.html";
    }
    
    @PostMapping("/vista/{id}")
    public String visto(@PathVariable Long id, ModelMap modelo){
        
        return "noticiaVista.html";       
    }
    
    @GetMapping("/eliminar/{id}")
    public String borrar(@PathVariable Long id){
        noticiaService.eliminar(id);
        return "redirect:/noticia/lista";
    }

}//The end
