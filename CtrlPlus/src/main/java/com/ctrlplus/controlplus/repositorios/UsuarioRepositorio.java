package com.ctrlplus.controlplus.repositorios;

import com.ctrlplus.controlplus.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, String> {
     @Query("SELECT l FROM Usuario l WHERE  l.mail = :mail")
    public Usuario buscarPorMail(@Param("mail") String mail);

}
