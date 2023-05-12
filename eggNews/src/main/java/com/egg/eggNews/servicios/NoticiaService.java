/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.egg.eggNews.servicios;

import com.egg.eggNews.Exceptions.MyException;
import com.egg.eggNews.entidades.Noticia;
import com.egg.eggNews.repositorios.NoticiaRepositorio;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Kidver
 */
@Service
public class NoticiaService {

    @Autowired
    private NoticiaRepositorio noticiaRepositorio;

    @Transactional
    public void crearNoticia(String titulo, String cuerpo) throws MyException {

//        if (titulo.isEmpty()) {
//            throw new MyException("Debes ingresar un titulo");
//        }
//        
//        if (cuerpo.isEmpty() || titulo == null) {
//            throw new MyException("Debes ingresar el cuerpo de la noticia");
//        }
        this.validar(titulo, cuerpo);

        Noticia noticia = new Noticia();
        noticia.setTitulo(titulo);
        noticia.setCuerpo(cuerpo);

        noticiaRepositorio.save(noticia);

    }

    public Noticia getOne(Long id){
        return noticiaRepositorio.getOne(id);
    }
    
    public List<Noticia> listarNoticias() {
        List<Noticia> noticias;
        noticias = noticiaRepositorio.findAll();
        return noticias;
    }

    public void modificarNoticia(Long id, String titulo, String cuerpo) throws MyException {

        this.validar(titulo, cuerpo);

        Optional<Noticia> respuesta = noticiaRepositorio.findById(id);

        if (respuesta.isPresent()) {
            Noticia noticia = respuesta.get();
            noticia.setTitulo(titulo);
            noticia.setCuerpo(cuerpo);
            noticiaRepositorio.save(noticia);
        }
    }

        
    private void validar(String titulo, String cuerpo) throws MyException {

        if (titulo.isEmpty()) {
            throw new MyException("Debes ingresar un titulo");
        }

        if (cuerpo.isEmpty() || cuerpo == null) {
            throw new MyException("Debes ingresar el cuerpo de la noticia");
        }
    }

}//The end
