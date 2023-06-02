/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.egg.eggNews.servicios;

import com.egg.eggNews.Exceptions.MyException;
import com.egg.eggNews.entidades.Imagen;
import com.egg.eggNews.repositorios.ImagenRepository;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Kidver
 */
@Service
public class ImagenService {

    @Autowired
    private ImagenRepository imagenRepository;
    
    @Transactional
    public Imagen guardar(MultipartFile archivo) throws MyException{
        if (archivo != null) {
            try {
                Imagen imagen = new Imagen();
                imagen.setMime(archivo.getContentType());
                imagen.setNomnbre(archivo.getName());
                imagen.setContenido(archivo.getBytes());

                return imagenRepository.save(imagen);

            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        
        return null;
    }
    
    @Transactional
    public Imagen actualizar(MultipartFile archivo, String idImagen ) throws MyException{
        
          if (archivo != null) {
            try {
                Imagen imagen = new Imagen();
                
                if (idImagen !=null) {
                    Optional<Imagen> respuesta = imagenRepository.findById(idImagen);
                    
                    if (respuesta.isPresent()) {
                        imagen = respuesta.get();
                    }
                }
                
                imagen.setMime(archivo.getContentType());
                imagen.setNomnbre(archivo.getName());
                imagen.setContenido(archivo.getBytes());

                return imagenRepository.save(imagen);

            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        
        return null;
    }

}//The end
