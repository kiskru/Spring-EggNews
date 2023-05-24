/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.egg.eggNews.repositorios;

import com.egg.eggNews.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Kidver
 */
@Repository
public interface UserRepository extends JpaRepository<Usuario, String> {
    
    @Query("SELECT u FROM Usuario u WHERE u.email = :email")
    public Usuario buscarPorEmail(@Param("email") String email);
    
    
    
    
}
